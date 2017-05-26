import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Albert Jin on 5/20/2017.
 */
public class ServerB extends Server implements Runnable{

    @Override
    public void run() {
        connect();
    }

    public void connect() {
        try {
            //make server socket and look for available client sockets
            ServerSocket serverS = new ServerSocket(900);
            //loop to display all incoming messages
            while (true) {
                System.out.println("4");
                Socket s = serverS.accept();
                System.out.println("Accepted");
                ClientUser user = new ClientUser(s, n, msgDisplay);
                Thread t = new Thread(user);
                t.start();
                System.out.println("thread started");
            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
