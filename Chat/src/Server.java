import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import javax.swing.*;

/**
 * Created by Alber on 5/18/2017.
 */
public class Server extends Application implements Runnable{
    Button sendButton;
    Button connectButton;
    TextField typeSpace;
    BorderPane layout;
    Label msgDisplay;
    Node n = new Node();


    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //set PrimaryStage size and title
        primaryStage.setTitle("ServerChat");
        primaryStage.setWidth(300);
        primaryStage.setHeight(300);

        //create sendbutton and typespace and set button action
        sendButton = new Button("send");
        connectButton = new Button("connect");
        typeSpace = new TextField();
        typeSpace.setPrefWidth(150);
        sendButton.setOnAction(e -> {
            String currMessage = typeSpace.getText();
            typeSpace.clear();
            msgDisplay.setText(msgDisplay.getText() + '\n' + currMessage);
            n.msgExists = true;
            n.msg = currMessage;
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


    public static void main(String[] args ) throws InterruptedException{
        Server s = new Server();
        ServerB sb = new ServerB();
        //Client a = new Client();
        //ClientB b = new ClientB();
        Thread t1 = new Thread(s);
        Thread t2 = new Thread(sb);
        //Thread t3 = new Thread(a);
        //Thread t4 = new Thread(b);
        t1.setDaemon(true);
        t2.setDaemon(true);
        t1.start();
        t2.start();
        //t3.start();
        //t4.start();
    }

}
