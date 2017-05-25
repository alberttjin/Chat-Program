import javafx.scene.control.TextArea;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Albert Jin on 5/23/2017.
 */
public class Server {
    Node n;
    ServerThread t;
    Thread t1;
    TextArea msgDisplay;

    Server(TextArea msgDisplay) {
        this.msgDisplay = msgDisplay;
        n = new Node();
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
                ss = new ServerSocket(1001);
                s = ss.accept();
                System.out.println("connection established");
                pw = new PrintWriter(s.getOutputStream());
                sc = new Scanner(s.getInputStream()).useDelimiter("\\A");
                while (true) {
                    if (sc.hasNextLine()) {
                        msgDisplay.appendText(sc.nextLine() + "\n");
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
