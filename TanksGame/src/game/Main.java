package game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int SIZE = 700;
    private static final int FPS = 60;
    private static final int MS_DELAY = 1000 / FPS;
    private static final double S_DELAY = 1.0 / FPS;

    private Game tanks;
    private Stage stage;
    private KeyFrame frame;
    private Timeline animation;

    private TanksUI uiManager;


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

    private void gameStart() {

    }

    private void configureStage(Scene startScene) {
    }

    @Override
    public void start(Stage s) throws Exception {
        this.stage = s;
        uiManager = new TanksUI(new GameStart(), new GameExit());
        Scene startScene = new StartScene(uiManager, SIZE).initScene();
        configureStage(startScene);
        stage.show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}