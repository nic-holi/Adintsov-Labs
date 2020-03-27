package game;

import javafx.scene.Scene;

public abstract class GameScene {
    protected TanksUI uiManager;
    protected final int SIZE;
    protected Game myGame;

    public GameScene(TanksUI manager, int SIZE) {
        uiManager = manager;
        this.SIZE = SIZE;
    }

    public GameScene(TanksUI manager, int SIZE, Game game) {
        this(manager, SIZE);
        myGame = game;
    }


    public abstract Scene initScene();
}