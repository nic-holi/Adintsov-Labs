package game.sprite;
import java.util.ArrayList;

import game.main.Game;


public class PlayerTank extends Tank {

    public static final long IMMORTAL_DELAY = 5000*1000*100;
    private long immortalStartTime = System.nanoTime();

    public long getImmortalStartTime() { return immortalStartTime; }

    public void setImmortalStartTime(long immortalStartTime) { this.immortalStartTime = immortalStartTime; }

    public PlayerTank(ArrayList<Sprite> elements) {
        super(elements);
        setRed();
        setBITMASK(Game.PLAYER_TANK_MASK);

    }

    public void update(double time) {
        super.update(time);
        missileDirection = getDirection() != Direction.NONE ? getDirection() : missileDirection;
        setDirection(Direction.NONE);

    }

    @Override
    protected void dealWithCollision(Sprite s) {
        if (s.getBITMASK() == Game.ENEMY_MISSILE_MASK
                || s.getBITMASK() == Game.ENEMY_TANK_MASK) {
            setHealth(0);
        }
    }


    public int getMissileMask() {
        return Game.PLAYER_MISSILE_MASK;
    }

    @Override
    public int compareTo(Sprite o) {
        return 0;
    }
}
