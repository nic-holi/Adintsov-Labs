package game.ui;

import game.sprite.Direction;
import game.sprite.PlayerTank;
import game.sprite.Sprite;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Game {
    private static final String TITLE = "Tank Battle";
    private Status status = Status.WAIT;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    private ArrayList<Sprite> elements = new ArrayList<Sprite>();
    private Scene myScene;

    public Scene getMyScene() {
        return myScene;
    }

    public void setMyScene(Scene myScene) {
        this.myScene = myScene;
    }

    private GraphicsContext gc;

    public GraphicsContext getGc() {
        return gc;
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }

    private PlayerTank playerTank;

    public PlayerTank getPlayerTank() {
        return playerTank;
    }

    public void setPlayerTank(PlayerTank playerTank) {
        this.playerTank = playerTank;
    }

    private int width, height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getTitle() {
        return TITLE;
    }

    private void renderElements() {
        elements.sort(null);
        for (Sprite e: elements) {
            e.render(gc);
        }
    }
    public Scene init(int width, int height) {
        setStatus(Status.PLAY);
        this.setWidth(width) ;
        this.setHeight(height);


        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");


        setGc(initGraphicsContext(root)) ;

        myScene = new Scene(root, width, height, Color.BLACK);
        // Respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }


    private void handleKeyInput(KeyCode code) {

        switch (code) {
            case SPACE:
                playerTank.fireMissile();
                break;
            case RIGHT:

                 playerTank.setDirection(Direction.RIGHT);
                break;
            case LEFT:

                playerTank.setDirection(Direction.LEFT);
                break;
            case UP:

                 playerTank.setDirection(Direction.UP);
                break;
            case DOWN:

                 playerTank.setDirection(Direction.DOWN);
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

    public static final int PLAYER_TANK_MASK = 1;
    public static final int PLAYER_MISSILE_MASK = 6;

}



