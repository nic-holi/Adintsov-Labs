package by.bsiur.game.main;

import by.bsiur.game.map.GameMap;
import by.bsiur.game.sprite.Direction;
import by.bsiur.game.sprite.PlayerTank;
import by.bsiur.game.sprite.Sprite;
import by.bsiur.game.stable.Home;
import by.bsiur.game.ui.GameHud;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class Game {
    public static final String TITLE = "Tank battle";
    private Status status = Status.WAIT;
    private int currentLevel = 0;

    public static long toLoseTime = System.nanoTime();
    public static final long LOSE_DELAY = 500 * 1000000;

    private long deadTime = System.nanoTime();
    public static final long DIE_DELAY = 1 * 1000000000L;

    private long passLevelTime = System.nanoTime();
    public static final long LEVEL_DELAY = 3 * 1000000000L;

    private int lives;
    private int score;
    public static final int INITIAL_LIVES = 3;
    public static final int SCORE_UNIT = 100;

    private Scene myScene;
    private GraphicsContext gc;
    private PlayerTank playerTank;
    private int width, height;

    private GameMap map;
    private int numLevels;
    private GameHud hudManager;

    public static final long GAME_TIME_SECONDS = 30;
    public static final long GAME_TIME = GAME_TIME_SECONDS * 1000000000L;
    private long startTime = System.nanoTime();

    private ArrayList<Sprite> elements = new ArrayList<Sprite>();


    public String getTitle () {
        return TITLE;
    }

    public GameHud getHudManager() {return hudManager;}

    public GameMap getMap() {return map;}

    public GraphicsContext getGc() {return gc;}

    public int getHeight() {return height; }

    public Status getStatus() {
        return status;
    }

    public int getScore() {
        return score;
    }

    public int getWidth() { return width; }

    public Scene getMyScene() { return myScene; }

    public Scene init (int width, int height) {
        status = Status.PLAY;
        this.width = width;
        this.height = height;
        elements = new ArrayList<Sprite>();
        startTime = System.nanoTime();
        lives = INITIAL_LIVES;
        score = 0;
        currentLevel = 0;

        hudManager = new GameHud(this);

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: black;");
        root.setTop(hudManager.initHud());

        map = new GameMap(width, height);
        map.init(elements);
        numLevels = map.numLevels();
        nextLevel();

        gc = initGraphicsContext(root);

        myScene = new Scene(root, width, height + hudManager.getHeight(), Color.BLACK);
        // Respond to input
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }


    public void step (double elapsedTime) {
        if (lives <= 0 && status != Status.TOLOSE) {
            setToLose();
            return;
        }
        if (status == Status.PLAY && System.nanoTime() - startTime > GAME_TIME) {
            if (currentLevel >= numLevels) {
                status = Status.WIN;
                return;
            }
            gc.clearRect(0, 0, width, height);
            showScore();
            setBetween();
        }
        if (status == Status.BETWEEN) {
            if (System.nanoTime() - passLevelTime > LEVEL_DELAY) {
                nextLevel();
            }
            return;
        }
        if (status == Status.TOLOSE
                && System.nanoTime() - toLoseTime > LOSE_DELAY) {
            status = Status.LOST;
            return;
        }

        updateElements(elapsedTime);
        detectCollisions();
        renderElements();
        hudManager.updateHud();
        map.spawnTank();
    }


    public void setToLose() {
        status = Status.TOLOSE;
        toLoseTime = System.nanoTime();
    }





    private void nextLevel() {
        if (currentLevel >= numLevels) {
            status = Status.WIN;
            return;
        }
        elements.removeAll(elements);
        startTime = System.nanoTime();
        map.buildMap(currentLevel);
        playerTank = map.getPlayerTank();
        status = Status.PLAY;
        currentLevel++;
    }

    private void handleKeyInput (KeyCode code) {
        if (System.nanoTime() - deadTime < DIE_DELAY) {
            return;
        }
        switch (code) {
            case SPACE:
                playerTank.fireMissile();
                break;
            case RIGHT:
            case D:
                playerTank.setDirection(Direction.RIGHT);
                break;
            case LEFT:
            case A:
                playerTank.setDirection(Direction.LEFT);
                break;
            case UP:
            case W:
                playerTank.setDirection(Direction.UP);
                break;
            case DOWN:
            case S:
                playerTank.setDirection(Direction.DOWN);
                break;

            default:
                break;
        }
    }


    private void detectCollisions() {
        for (int i = 0; i < elements.size(); i++) {
            for (int j = i + 1; j < elements.size(); j++) {
                Sprite e1 = elements.get(i);
                Sprite e2 = elements.get(j);
                if (!e1.intersects(e2)) continue;
                if ((e1.getBITMASK() & e2.getBITMASK()) != 0) {
                    e1.handleCollision(e2);;
                    e2.handleCollision(e1);;
                }
            }
        }
    }



    public int getCurrentLevel() {
        return currentLevel;
    }


    public long getStartTime() {
        return startTime;
    }


    public int getLives() {
        return lives;
    }

    private GraphicsContext initGraphicsContext(BorderPane root) {
        Canvas canvas = new Canvas(width, height);
        canvas.setStyle("-fx-background-color: black;");
        root.setCenter(canvas);
        return canvas.getGraphicsContext2D();
    }

    private void renderElements() {
        elements.sort(null);
        for (Sprite e: elements) {
            e.render(gc);
        }
    }

    private void updateElements(double elapsedTime) {
        gc.clearRect(0, 0, width, height);
        int i = 0;
        while (i < elements.size()) {
            Sprite e = elements.get(i);
            if (e.isAlive()) {
                e.update(elapsedTime);
                i++;
            }
            else {
                if (e instanceof Home) {
                    if (status == Status.PLAY) {
                        setToLose();
                    }
                    i++;
                    continue;
                }
                elements.remove(i);
                if (e.getBITMASK() == playerTank.getBITMASK()) {
                    playerTank = map.revivePlayerTank();
                    lives--;
                    deadTime = System.nanoTime();
                }
                else if (e.getBITMASK() == Game.ENEMY_TANK_MASK) {
                    score += SCORE_UNIT;
                }
            }
        }
    }

    private void showScore() {
        gc.setFill(Color.WHITE);
        gc.setFont(new Font(20));
        gc.fillText("Current Score: " + getScore(), width / 2 - 80, height / 2);
    }

    private void setBetween() {
        passLevelTime = System.nanoTime();
        status = Status.BETWEEN;
    }

    public static final int PLAYER_TANK_MASK = 1;
    public static final int ENEMY_TANK_MASK = 3;
    public static final int PLAYER_MISSILE_MASK = 6;
    public static final int ENEMY_MISSILE_MASK = 9;
    public static final int STABLE_MASK = 15;

}



