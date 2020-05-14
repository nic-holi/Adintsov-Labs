package game.ui;

import game.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TanksUI {
    private EventHandler<ActionEvent> gameStart;
    private EventHandler<ActionEvent> gameExit;
    public EventHandler<ActionEvent> getGameStart() {
        return gameStart;
    }

    public void setGameStart(EventHandler<ActionEvent> gameStart) {
        this.gameStart = gameStart;
    }

    private boolean didInputName;

    public TanksUI(EventHandler<ActionEvent> start) {

        setGameStart(start);
        refreshGame();
    }

    public Button initStartButton() {
        Button startButton = new Button("Start Game");
        startButton.setPrefWidth(120);
        startButton.setOnAction(getGameStart());
        return startButton;
    }

    public Button initExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(120);
        exitButton.setOnAction(gameExit);
        return exitButton;
    }

    public void refreshGame() {
        didInputName = false;
    }
}

