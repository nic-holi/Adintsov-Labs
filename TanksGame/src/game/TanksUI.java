package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class TanksUI {
    private EventHandler<ActionEvent> gameStart;
    private boolean didInputName;

    public TanksUI(EventHandler<ActionEvent> start) {
        gameStart = start;
        refreshGame();
    }

    public Button initStartButton() {
        Button startButton = new Button("Start Game");
        startButton.setPrefWidth(120);
        startButton.setOnAction(gameStart);
        return startButton;
    }
    public void refreshGame() {
        didInputName = false;
    }
}
