package game.sprite;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.ui.Game;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class PlayerTank extends Tank {

    private static final long IMMORTAL_DELAY = 5000*1000*100;


    public PlayerTank(ArrayList<Sprite> elements) {
        super(elements);
        setWhite();
        setBITMASK(Game.PLAYER_TANK_MASK);

    }

    public void update(double time) {
        super.update(time);
        setMissileDirection(getDirection() != Direction.NONE ? getDirection() : getMissileDirection()) ;
        setDirection(Direction.NONE);

    }

    @Override
    protected void dealWithCollision(Sprite s) {

    }


    public int getMissileMask() {
        return Game.PLAYER_MISSILE_MASK;
    }



    @Override
    public int compareTo(Sprite o) {
        return 0;
    }
}
