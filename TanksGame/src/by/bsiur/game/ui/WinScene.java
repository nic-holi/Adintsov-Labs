package by.bsiur.game.ui;

import by.bsiur.game.main.Game;

import by.bsiur.game.scene.GameScene;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class WinScene extends GameScene {
	
	public WinScene(TanksUI manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	
    	int score= getTanks().getScore();;

		Label indicator = new Label("You Won!\nScore: " + score);
		root.getChildren().add(indicator);
		if (getUiManager().getBoard().canGetOn(score)) {
			root.getChildren().add(getUiManager().initNameInput(score));
		}
    	indicator.setFont(new Font(20));
    	Button startButton = getUiManager().initStartButton();
		Button leadersButton = getUiManager().initLeadersButton();
    	startButton.setText("Play Again");
    	Button exitButton = getUiManager().initExitButton();
    	
    	root.getChildren().addAll(startButton,leadersButton, exitButton);
    	Scene winScene = new Scene(root, getSIZE(), getSIZE());
    	return winScene;
    }
}
