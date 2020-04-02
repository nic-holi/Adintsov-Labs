package game.scene;

import game.UI.Game;
import game.UI.TanksUI;
import javafx.scene.Scene;

public abstract class GameScene {
    protected TanksUI uiManager;
    protected final int SIZE;
    protected Game tanks;

    public GameScene(TanksUI manager, int SIZE) {
        uiManager = manager;
        this.SIZE = SIZE;
    }

    public GameScene(TanksUI manager, int SIZE, Game game) {
        this(manager, SIZE);
        tanks = game;
    }


    public abstract Scene initScene();
}