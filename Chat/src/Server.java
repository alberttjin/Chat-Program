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

import javax.swing.*;

/**
 * Created by Alber on 5/18/2017.
 */
public class Server extends Application{
    Button sendButton;
    TextField typeSpace;
    String currMessage;
    ServerSocket serverS;
    Socket s;
    DataInputStream streamIn;
    DataOutputStream streamOut;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //set PrimaryStage size and title
        primaryStage.setTitle("Chat");
        primaryStage.setWidth(860);
        primaryStage.setHeight(600);

        //create sendbutton and typespace and set button action
        sendButton = new Button("send");
        typeSpace = new TextField();
        typeSpace.setPrefWidth(800);
        sendButton.setOnAction(e -> {
            currMessage = typeSpace.getText();
            typeSpace.clear();
        });

        //create layout and set new scene
        BorderPane layout = new BorderPane();
        HBox tempbox = new HBox();
        tempbox.getChildren().addAll(typeSpace, sendButton);
        layout.setBottom(tempbox);

        Scene newscene = new Scene(layout);
        primaryStage.setScene(newscene);
        primaryStage.show();

    }
    public static void main(String[] args ){
        launch();
        try {


        } catch(Exception e) {

        }
    }
}
