package game.main;

import game.scene.OverScene;
import game.ui.TanksUI;
import game.scene.StartScene;
import game.ui.WinScene;
import javafx.animation.KeyFrame;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public static final int SIZE = 680;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Game myGame;

    private Stage stage;
    private KeyFrame frame;
    private Timeline animation;

    private TanksUI uiManager;

    public Game getMyGame() {
        return myGame;
    }

    class GameStart implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            gameStart();
        }
    }

    class GameExit implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            stage.close();
        }
    }


    @Override
    public void start (Stage s) {
        this.stage = s;

        uiManager = new TanksUI(new GameStart());
        Scene startScene = new StartScene(uiManager, SIZE).initScene();
        configureStage(startScene);
        stage.show();
    }


    private void gameStart() {

        myGame = new Game();
        stage.setTitle(myGame.getTitle());
        uiManager.refreshGame();


        Scene gameScene = myGame.init(SIZE, SIZE);
        stage.setScene(gameScene);


        frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
                e -> step(SECOND_DELAY));
        animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }


    public void gameWin() {

        Scene winScene = new WinScene(uiManager, SIZE, myGame).initScene();
        stage.setScene(winScene);
        clearGame();
    }


    public void gameOver() {

        Scene overScene = new OverScene(uiManager, SIZE, myGame).initScene();
        stage.setScene(overScene);
        clearGame();
    }


    private void step(double elapsedTime) {
        switch (myGame.getStatus()) {
            case LOST:
                gameOver();
                return;
            case WIN:
                gameWin();
                return;
            default:
                break;
        }
        myGame.step(elapsedTime);
    }


    private void configureStage(Scene startScene) {
        stage.setTitle("Tank Battle");
        stage.setScene(startScene);
        stage.setResizable(false);
    }


    private void clearGame() {
        myGame = null;
        frame = null;
        if (animation != null)
            animation.stop();
        animation = null;
    }


    public static void main (String[] args) {
        launch(args);
    }
}
