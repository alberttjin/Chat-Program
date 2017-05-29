import javafx.scene.control.TextArea;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Albert Jin on 5/23/2017.
 */
public class Server {
    ServerThread t;
    Thread t1;
    TextArea msgDisplay;

    Server(TextArea msgDisplay) {
        this.msgDisplay = msgDisplay;
        t = new ServerThread();
        t1 = new Thread(t);
        t1.setDaemon(true);
    }

    public void start() {
        t1.start();
    }

    public void send(String msg) {
        t.pw.println(msg);
        t.pw.flush();
        System.out.println("sent");
    }

    private class ServerThread implements Runnable{
        private ServerSocket ss;
        private Socket s;
        private PrintWriter pw;
        private Scanner sc;

        @Override
        public void run() {
            try {
                while (true) {
                    ss = new ServerSocket(1001);
                    s = ss.accept();
                    pw = new PrintWriter(s.getOutputStream());
                    System.out.println("connection established");
                    /*pw = new PrintWriter(s.getOutputStream());
                    sc = new Scanner(s.getInputStream()).useDelimiter("\\A");
                    while (true) {
                        if (sc.hasNextLine()) {
                            msgDisplay.appendText("Client: " + sc.nextLine() + "\n");
                        }
                    }*/
                    Thread t1 = new Thread(new ServerThread2(s));
                    t1.start();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    private class ServerThread2 implements Runnable {
        Socket s;
        //PrintWriter pw;
        Scanner sc;

        ServerThread2(Socket s) {
            this.s = s;
        }

        @Override
        public void run() {
            try {
                //pw = new PrintWriter(s.getOutputStream());
                sc = new Scanner(s.getInputStream()).useDelimiter("\\A");
                while (true) {
                    if (sc.hasNextLine()) {
                        msgDisplay.appendText("Client: " + sc.nextLine() + "\n");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
