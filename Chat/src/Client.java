import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Alber on 5/18/2017.
 */
public class Client extends Application {
    /*Button sendButton;
    Button connectButton;
    TextField typeSpace;
    String currMessage;
    BorderPane layout;
    Label msgDisplay;
    Socket s;
    DataInputStream streamIn;
    DataOutputStream streamOut;*/
    static Button sendButton;
    static Button connectButton;
    static TextField typeSpace;
    static String currMessage;
    static BorderPane layout;
    static Label msgDisplay;
    static Socket s;
    static ServerSocket serverS;
    static DataInputStream streamIn;
    static DataOutputStream streamOut;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //set PrimaryStage size and title
        primaryStage.setTitle("Chat");
        primaryStage.setWidth(900);
        primaryStage.setHeight(600);

        //create sendbutton and typespace and set button action
        sendButton = new Button("send");
        connectButton = new Button("connect");
        typeSpace = new TextField();
        typeSpace.setPrefWidth(800);
        sendButton.setOnAction(e -> {
            currMessage = typeSpace.getText();
            typeSpace.clear();
            msgDisplay.setText(msgDisplay.getText() + '\n' + currMessage);
            try {
                streamOut.writeBytes(currMessage);
            } catch (Exception f) {
            }
        });
        connectButton.setOnAction(e -> {
            connect();
        });
        //create layout and set new scene
        layout = new BorderPane();
        msgDisplay = new Label();
        HBox tempbox = new HBox();
        tempbox.getChildren().addAll(typeSpace, sendButton, connectButton);
        layout.setBottom(tempbox);
        layout.setTop(msgDisplay);
        Scene newscene = new Scene(layout);
        primaryStage.setScene(newscene);
        primaryStage.show();

    }
    public static void main(String[] args ){
        //launch();
        connect();
    }

    public static void connect() {
        //incoming message
        String newMsg;
        try {
            //make server socket and look for available client sockets
            System.out.println("in try");
            s = new Socket("10.0.0.20", 1201);
            System.out.print("connection established");
            streamIn = new DataInputStream(s.getInputStream());
            streamOut = new DataOutputStream(s.getOutputStream());

            //loop to display all incoming messages
            while (true) {
                newMsg = streamIn.readUTF();
                msgDisplay.setText(msgDisplay.getText() + '\n' + newMsg);
                System.out.println(s.getLocalAddress());
                System.out.println(s.getLocalPort());
                System.out.println(s.getLocalAddress().getHostName());

            }

        } catch(Exception e) {
            System.out.println(e);
        }
    }
}
