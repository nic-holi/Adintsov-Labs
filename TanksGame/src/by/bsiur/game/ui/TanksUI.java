package by.bsiur.game.ui;

import by.bsiur.game.leader.Leader;
import by.bsiur.game.leader.LeaderBoard;
import by.bsiur.game.main.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TanksUI {
    private EventHandler<ActionEvent> gameStart;
    private EventHandler<ActionEvent> gameExit;
    private EventHandler<ActionEvent> showLeaders;
    public EventHandler<ActionEvent> getGameStart() {
        return gameStart;
    }
    private boolean didInputName;
    private LeaderBoard board;
    public void setGameStart(EventHandler<ActionEvent> gameStart) {
        this.gameStart = gameStart;
    }

    public EventHandler<ActionEvent> getShowLeaders() { return showLeaders; }

    public void setShowLeaders(EventHandler<ActionEvent> showLeaders) { this.showLeaders = showLeaders; }

    public void setGameExit(EventHandler<ActionEvent> gameExit) { this.gameExit = gameExit; }

    public EventHandler<ActionEvent> getGameExit() { return gameExit; }

    public TanksUI(EventHandler<ActionEvent> start,
                   EventHandler<ActionEvent> leaders,
                   EventHandler<ActionEvent> exit) {

        setGameStart(start);
        setShowLeaders(leaders);
        setGameExit(exit);
        refreshGame();
        setBoard(new LeaderBoard());
    }

    public Button initStartButton() {
        Button startButton = new Button("Start Game");
        startButton.setPrefWidth(120);
        startButton.setOnAction(getGameStart());
        return startButton;
    }
    public Button initLeadersButton() {
        Button leadersButton = new Button("Leader Board");
        leadersButton.setPrefWidth(120);
        leadersButton.setOnAction(getShowLeaders());
        return leadersButton;
    }



    public Button initExitButton() {
        Button exitButton = new Button("Exit");
        exitButton.setPrefWidth(120);
        exitButton.setOnAction(gameExit);
        return exitButton;
    }
    public VBox initNameInput(int score) {
        VBox whole = new VBox();
        whole.setAlignment(Pos.CENTER);
        Label indicator = new Label("You just got on the leader board!");
        whole.setSpacing(20);
        HBox box = new HBox();
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(15, 12, 15, 12));
        box.setSpacing(20);
        Label prompt = new Label("Name:");
        TextField input = new TextField();
        Button confirm = new Button("OK");
        confirm.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (didInputName) {
                    indicator.setText("You've already put your name in!");
                    return;
                }
                didInputName = true;
                Leader l = new Leader(input.getText(), score);
                getBoard().putOn(l);
                getBoard().save();
                indicator.setText("Succeeded!");
            }
        });
        box.getChildren().addAll(prompt, input, confirm);
        whole.getChildren().addAll(indicator, box);
        return whole;
    }

    public void refreshGame() {
        didInputName = false;
    }

    public LeaderBoard getBoard() {
        return board;
    }
    private void setBoard(LeaderBoard board) {
        this.board = board;
    }
}

