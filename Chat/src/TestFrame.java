import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.w3c.dom.events.Event;

import javax.swing.*;

/**
 * Created by Alber on 5/17/2017.
 */
public class TestFrame extends Application implements EventHandler<ActionEvent>{
    Button button;
    TextField typespace;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chat");
        primaryStage.setWidth(860);
        primaryStage.setHeight(600);
        button = new Button("send");
        typespace = new TextField();
        typespace.setPrefWidth(800);
        button.setOnAction(e -> {

        });
        BorderPane layout = new BorderPane();
        HBox tempbox = new HBox();
        tempbox.getChildren().addAll(typespace, button);
        layout.setBottom(tempbox);
        Scene newscene = new Scene(layout);
        primaryStage.setScene(newscene);
        primaryStage.show();


    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == button) {
            Button newButton = new Button();
            HBox tempbox = new HBox();
            tempbox.getChildren().add(newButton);
            Scene newScene2 = new Scene(tempbox);
            Stage newStage = new Stage();
            newStage.setScene(newScene2);
            newStage.show();
        }
    }
}
