package game;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

enum Status {
    Wait, Play;
}

public class Game {
    private static final String TITLE = "Tank Battle";
    private Status status = Status.Wait;


    private Scene myScene;
    private GraphicsContext gc;
    private PlayerTank playerTank;
    private int width, height;


    public String getTitle() {
        return TITLE;
    }

    public Scene init(int width, int height) {
        status = Status.Play;
        this.width = width;
        this.height = height;


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");


        gc = initGraphicsContext(root);

        myScene = new Scene(root, width, height, Color.BLACK);
        // Respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }


    private void handleKeyInput(KeyCode code) {

        switch (code) {
            case SPACE:
                //playerTank.fire();
                break;
            case RIGHT:

                // playerTank.setDirection(Direction.RIGHT);
                break;
            case LEFT:

                //playerTank.setDirection(Direction.LEFT);
                break;
            case UP:

                // playerTank.setDirection(Direction.UP);
                break;
            case DOWN:

                // playerTank.setDirection(Direction.DOWN);
                break;
            default:
                break;
        }
    }


    private GraphicsContext initGraphicsContext(BorderPane root) {
        Canvas canvas = new Canvas(width, height);
        canvas.setStyle("-fx-background-color: black;");
        root.setCenter(canvas);
        return canvas.getGraphicsContext2D();
    }

}



