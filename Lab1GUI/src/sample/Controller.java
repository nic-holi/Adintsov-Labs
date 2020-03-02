package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.Initialization;

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
    private Button firstAddSecondButton;

    @FXML
    void initialize() {
        Initialization initialization = new Initialization();

        copyFrom1To2Button.setOnAction(event -> {
            String text1= firstField.getText();
            secondField.setText(text1);
        });
        copyFrom2To3Button.setOnAction(Event -> {
            String text2= secondField.getText();
            thirdField.setText(text2);
        });

        firstAddSecondButton.setOnAction(Event -> {
                String text1= firstField.getText();
                String text2= secondField.getText();
                String text3= initialization.firstAddSecond(text1,text2);
                thirdField.setText(text3);
        });
    }
}


