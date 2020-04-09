package game.scene;

import game.ui.TanksUI;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


//import java.awt.*;

import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;


public class StartScene extends GameScene {

    public StartScene(TanksUI manager, int SIZE) {
        super(manager, SIZE);
    }

    private Node initStartViewButtons() {
        VBox buttons = new VBox();

        buttons.setPadding(new Insets(15, 12, 15, 12));
        buttons.setSpacing(100);

        Button startButton = getUiManager().initStartButton();

        Text text = new Text();
        text.setFont(new Font(16));
        text.setWrappingWidth(400);
        text.setTextAlignment(TextAlignment.CENTER);
        text.setText("WASD or arrow keys to move around\n\nSpace to shoot\n");

        buttons.getChildren().addAll(text, startButton);
        buttons.setAlignment(Pos.CENTER);

        return buttons;
    }

    private Label initTitle() {
        Label title = new Label("Tank Battle");
        title.setFont(new Font("Impact",30));
        title.setPadding(new Insets(15, 15, 15, 15));
        title.setTextAlignment(TextAlignment.CENTER);
        return title;
    }

    public Scene initScene() {
        BorderPane root = new BorderPane();

        Node startViewButtons = initStartViewButtons();
        Label title = initTitle();

        root.setTop(title);
        root.setCenter(startViewButtons);
        BorderPane.setAlignment(title, Pos.CENTER);
        Scene scene = new Scene(root, getSIZE(), getSIZE());
        File f = new File("src/game/scene/style.css");
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));

        return scene;
    }
}
