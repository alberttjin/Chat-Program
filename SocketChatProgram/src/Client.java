import javafx.scene.control.TextArea;
import sun.misc.IOUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Created by Albert Jin on 5/23/2017.
 */
public class Client {
    ClientThread t;
    TextArea msgDisplay;
    Thread t1;

    Client(TextArea msgDisplay) {
        this.msgDisplay = msgDisplay;
        t = new ClientThread();
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

    private class ClientThread implements Runnable{
        private Socket s;
        private PrintWriter pw;
        private Scanner sc;

        @Override
        public void run() {
            try {
                s = new Socket("10.0.0.20", 1001);
                System.out.println("connection established");
                pw = new PrintWriter(s.getOutputStream());
                sc = new Scanner(s.getInputStream()).useDelimiter("\\A");
                while (true) {
                    if (sc.hasNextLine()) {
                        msgDisplay.appendText("Server: " + sc.nextLine() + "\n");
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
