package game.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TanksUI {
    private EventHandler<ActionEvent> gameStart;

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
    public void refreshGame() {
        didInputName = false;
    }
}

