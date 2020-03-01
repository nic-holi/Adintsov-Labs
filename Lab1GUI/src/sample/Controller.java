package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button copyFrom1To2Button;

    @FXML
    private Button copyFrom2To3Button;

    @FXML
    private TextField firstField;

    @FXML
    private TextField secondField;

    @FXML
    private TextField thirdField;

    @FXML
    void initialize() {
        copyFrom1To2Button.setOnAction(event -> {
            secondField.setText(firstField.getText());

        });
        copyFrom2To3Button.setOnAction(Event -> {
            thirdField.setText(secondField.getText());
        } );

    }
}
