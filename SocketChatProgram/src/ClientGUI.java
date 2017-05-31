import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/**
 * Created by Albert Jin on 5/23/2017.
 */
public class ClientGUI extends Application {
    private TextArea msgDisplay = new TextArea();
    Client client;
    String clientUsername;

    /*public void init() {
        client.start();
    }*/

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");
        Text username = new Text("Enter Your Username");
        username.setScaleX(3);
        username.setScaleY(3);
        TextField usernameInput = new TextField();
        usernameInput.setOnAction(event -> {
            clientUsername = usernameInput.getText();
            usernameInput.clear();
            makeChat(primaryStage);
        });
        VBox login = new VBox(username, usernameInput);
        login.setAlignment(Pos.CENTER);
        login.setSpacing(80);
        login.setPrefSize(500, 500);
        Scene loginScreen = new Scene(login);
        primaryStage.setScene(loginScreen);
        primaryStage.show();
    }
    private void makeChat(Stage primaryStage) {
        client = new Client(msgDisplay, clientUsername);
        client.start();
        primaryStage.setTitle("ClientChat");
        msgDisplay.setEditable(false);
        TextField input = new TextField();
        input.setPrefSize(500, 30);
        msgDisplay.setPrefSize(500, 470);
        VBox chat = new VBox(msgDisplay, input);
        chat.setPrefSize(500, 500);
        input.setOnAction(event -> {
            String msg = input.getText();
            input.clear();
            msgDisplay.appendText(clientUsername + ": " + msg + "\n");
            try {
                client.send(msg);
            } catch (Exception e) {
                System.out.println("Server not connected");
            }
        });
        Scene newScene = new Scene(chat);
        primaryStage.setScene(newScene);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
