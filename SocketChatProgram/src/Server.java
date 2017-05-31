import javafx.scene.control.TextArea;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Albert Jin on 5/23/2017.
 */
public class Server {
    ServerThread t;
    Thread t1;

    Server() {
        t = new ServerThread();
        t1 = new Thread(t);
        t1.setDaemon(true);
    }

    public void start() {
        t1.start();
        try {
            t1.join();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void send(String msg, Socket currUser) {
        for (Socket elem: t.connections.keySet()) {
            if (elem != currUser) {
                PrintWriter pw = t.connections.get(elem);
                pw.println(msg);
                pw.flush();
            }
        }
        System.out.println("sent");
    }
    public static void main (String[] args) {
        Server server = new Server();
        server.start();
    }

    private class ServerThread implements Runnable{
        private ServerSocket ss;
        private Socket s;
        private HashMap<Socket, PrintWriter> connections;

        @Override
        public void run() {
            try {
                connections = new HashMap<>();
                ss = new ServerSocket(1001);
                while (true) {
                    s = ss.accept();
                    connections.put(s, new PrintWriter(s.getOutputStream()));
                    System.out.println("new connection established");
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
                System.out.println("here");
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
                sc = new Scanner(s.getInputStream());
                while (true) {
                    if (sc.hasNextLine()) {
                        //msgDisplay.appendText(sc.nextLine() + "\n");
                        send(sc.nextLine(), this.s);
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
