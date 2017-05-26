import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Alber on 5/18/2017.
 */
public class Client extends Application implements Runnable{
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
        primaryStage.setTitle("ClientChat");
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

    public static void main(String[] args) throws InterruptedException{
        Client a = new Client();
        ClientB b = new ClientB();
        Thread t3 = new Thread(a);
        Thread t4 = new Thread(b);
        t3.setDaemon(true);
        t4.setDaemon(true);
        t3.start();
        t4.start();
    }

}
