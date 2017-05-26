/**
 * Created by Albert Jin on 5/23/2017.
 */
public class lol implements Runnable{
    @Override
    public void run() {
        while(true) {
            System.out.println("2");
        }
    }

    public static void main(String[] args) {
        lol a = new lol();
        lmao b = new lmao();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(b);
        t1.start();
        t2.start();
    }
}
