import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class lab1 extends Application {

    Button button;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Title of the Window");

        button= new Button();
        button.setText("Click");
        StackPane layout= new StackPane();
        layout.getChildren().add(button);

        Scene scene =new Scene(layout, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
