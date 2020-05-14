package game.ui;

import game.main.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class GameHud {
	private Text timeHud;
	private Text livesHud;
	private Text levelHud;
	private Game myGame;
	
	public GameHud(Game game) {
		myGame = game;
	}

	public void setLevelHud(Text levelHud) { this.levelHud = levelHud; }

	public void setLivesHud(Text livesHud) { this.livesHud = livesHud; }

	public void setMyGame(Game myGame) { this.myGame = myGame; }

	public void setTimeHud(Text timeHud) { this.timeHud = timeHud; }

	public Game getMyGame() { return myGame;	}

	public Text getLevelHud() { return levelHud;	}

	public Text getLivesHud() { return livesHud;	}

	public Text getTimeHud() { return timeHud;	}

	public Node initHud() {
		livesHud = new Text();
		configureGameHud(livesHud);
		timeHud = new Text();
		configureGameHud(timeHud);
		levelHud = new Text();
		configureGameHud(levelHud);
		HBox box = new HBox();
		box.getChildren().addAll(getLivesHud(), getTimeHud(), getLevelHud());
		box.setSpacing(200);
		BorderPane.setAlignment(box, Pos.CENTER);
		return box;
	}
	
	public void updateHud() {
		updateLivesHud();
		updateTimeHud();
		updateLevelHud();
	}
	
	public double getHeight() {
		return getLivesHud().getLayoutBounds().getHeight();
	}
	
	private void configureGameHud(Text hud) {
		hud.setFont(new Font(20));
		hud.setFill(Color.WHITE);
	}
	
	public void updateLivesHud() {
		getLivesHud().setText(String.format("Lives: %d", getMyGame().getLives()));
	}
	
	public void updateTimeHud() {
		getTimeHud().setText("Time: " + (Game.GAME_TIME_SECONDS - (System.nanoTime() - getMyGame().getStartTime()) / 1000000000L));
	}
	
	public void updateLevelHud() {
		getLevelHud().setText("Level: " + getMyGame().getCurrentLevel());
	}
}
