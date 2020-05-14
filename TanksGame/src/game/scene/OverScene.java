package game.scene;

import game.main.Game;
import game.scene.GameScene;
import game.ui.TanksUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class OverScene extends GameScene {
	
	public OverScene(TanksUI manager, int SIZE, Game game) {
		super(manager, SIZE, game);
	}
	
	public Scene initScene() {
    	Label indicator = new Label("Game Over\nScore: " /*+ myGame.getScore()*/);
    	indicator.setFont(new Font(20));
    	Button startButton = getUiManager().initStartButton();
    	startButton.setText("Play Again");

    	Button exitButton = getUiManager().initExitButton();
    	VBox root = new VBox();
    	root.setSpacing(60);
    	root.setAlignment(Pos.CENTER);
    	root.getChildren().add(indicator);
    	//int score = myGame.getScore();
    	//if (getUiManager().getBoard().canGetOn(score)) {
    ///		root.getChildren().add(uiManager.initNameInput(score));
    //	}
    	root.getChildren().addAll(startButton, exitButton);
    	Scene overScene = new Scene(root, getSIZE(), getSIZE());
    	return overScene;
    }
}
