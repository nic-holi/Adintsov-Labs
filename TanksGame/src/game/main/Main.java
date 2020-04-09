package game.main;

import game.ui.Game;
import game.ui.TanksUI;
import game.scene.StartScene;
import javafx.animation.KeyFrame;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.Stage;


public class Main extends Application {
    private static final int SIZE = 500;
    private static final int FPS = 60;

    public static int getSIZE() {
        return SIZE;
    }

    public static int getFPS() {
        return FPS;
    }

    private Game tanks;

    public Game getTanks() {
        return tanks;
    }

    public void setTanks(Game tanks) {
        this.tanks = tanks;
    }

    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    private KeyFrame frame;

    public KeyFrame getFrame() {
        return frame;
    }

    public void setFrame(KeyFrame frame) {
        this.frame = frame;
    }

    private TanksUI uiManager;

    public void setUiManager(TanksUI uiManager) {
        this.uiManager = uiManager;
    }

    public TanksUI getUiManager() {
        return uiManager;
    }

    class GameStart implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            gameStart();
        }
    }
    private void gameStart() {
        // create your own game here
        tanks = new Game();

        getStage().setTitle(tanks.getTitle());
        getUiManager().refreshGame();

        // attach game to the stage and display it
        Scene gameScene = tanks.init(getSIZE(), getSIZE());
        getStage().setScene(gameScene);

    }


    private void configureStage(Scene startScene) {
        getStage().setTitle("Tank Battle");
        getStage().setScene(startScene);
        getStage().setResizable(false);
    }

    @Override
    public void start(Stage s) throws Exception {
        this.setStage(s);
        uiManager = new TanksUI(new GameStart());
        Scene startScene = new StartScene(getUiManager(), getSIZE()).initScene();
        configureStage(startScene);
        getStage().show();
    }

    public static void main (String[] args) {
        launch(args);
    }
}