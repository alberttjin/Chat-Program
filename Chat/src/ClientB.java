import java.net.Socket;

public class ClientB extends Client implements Runnable {

    @Override
    public void run() {
        connect();
    }

    public void connect() {
        try {
            //make server socket and look for available client sockets
            Socket s = new Socket("10.0.0.20", 900);
            System.out.println("connection established");
            ClientUser user = new ClientUser(s, n, msgDisplay);
            Thread t = new Thread(user);
            t.start();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
