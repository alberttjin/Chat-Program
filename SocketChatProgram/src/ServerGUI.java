import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * Created by Albert Jin on 5/23/2017.
 */
public class ServerGUI extends Application {
    private TextArea msgDisplay = new TextArea();
    Server server = new Server();

    public void init() {
        server.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("ServerChat");
        TextField input = new TextField();
        input.setPrefSize(500, 30);
        msgDisplay.setPrefSize(500, 470);
        VBox chat = new VBox(msgDisplay, input);
        chat.setPrefSize(500, 500);
        input.setOnAction(event -> {
            String msg = input.getText();
            input.clear();
            msgDisplay.appendText("Server: " + msg + "\n");
            try {
                server.send(msg, null);
            } catch (Exception e) {
                System.out.println("Client not connected");
            }
        });
        Scene newScene = new Scene(chat);
        primaryStage.setScene(newScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
