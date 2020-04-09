package game.scene;

import game.ui.Game;
import game.ui.TanksUI;
import javafx.scene.Scene;

public abstract class GameScene {
    private TanksUI uiManager;

    public void setUiManager(TanksUI uiManager) {
        this.uiManager = uiManager;
    }

    public TanksUI getUiManager() {
        return uiManager;
    }

    private int SIZE;

    public void setSIZE(int SIZE) {
        this.SIZE = SIZE;
    }

    public int getSIZE() {
        return SIZE;
    }

    private Game tanks;

    public void setTanks(Game tanks) {
        this.tanks = tanks;
    }

    public Game getTanks() {
        return tanks;
    }

    public GameScene(TanksUI manager, int SIZE) {
        setUiManager(manager);
        setSIZE(SIZE);
    }

    public GameScene(TanksUI manager, int SIZE, Game game) {
        this(manager, SIZE);
        setTanks(game);
    }


    public abstract Scene initScene();
}