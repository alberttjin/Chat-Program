import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Alber on 5/19/2017.
 */
public class ServerUser implements Runnable{
    Socket s;
    Node n;
    Label msgDisplay;

    ServerUser(Socket s) {
        this.s = s;
        this.n = n;
        this.msgDisplay = msgDisplay;
    }

    @Override
    public void run() {
        try {
            Scanner sc = new Scanner(s.getInputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            PrintWriter pw = new PrintWriter(s.getOutputStream());

            while (true) {
                System.out.println("1");
                if (!n.msgExists) {
                    pw.println("hi");
                    pw.flush();
                    n.msgExists = false;
                    System.out.println("3");
                }
                System.out.println(sc.hasNext());
                if (sc.hasNext() && (msgDisplay != null)) {
                    System.out.println("2");
                    System.out.println(sc.nextLine());
                    msgDisplay.setText(msgDisplay.getText() + "\n" + sc.nextLine());
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
