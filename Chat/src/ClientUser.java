import javafx.scene.control.Label;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Alber on 5/19/2017.
 */
public class ClientUser implements Runnable{
    Socket s;
    Node n;
    Label msgDisplay;

    ClientUser(Socket s, Node n, Label msgDisplay) {
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
                if (n.msgExists) {
                    pw.println(n.msg);
                    pw.flush();
                    n.msgExists = false;
                }
                if (sc.hasNext()) {
                    //System.out.println("2");
                    System.out.println(sc.nextLine());
                    //msgDisplay.setText(msgDisplay.getText() + "\n" + sc.nextLine());
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
