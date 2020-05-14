package game.sprite;


import game.stable.Grass;
import game.stable.Water;

public class Missile extends Sprite {
    public static final String MISSILE_UP = "missile-up.gif";

    public static final String MISSILE_DOWN = "missile-down.gif";
    public static final String MISSILE_LEFT = "missile-left.gif";
    public static final String MISSILE_RIGHT = "missile-right.gif";
    public static final int SPEED = 500;



    public Missile(Direction direction, int mask) {
        setBITMASK(mask);
        switch (direction) {
            case UP:
                setImage(MISSILE_UP);
                setVelocityX(0);
                setVelocityY(-SPEED);
                break;
            case DOWN:
                setImage(MISSILE_DOWN);
                setVelocityX(0);
                setVelocityY(SPEED);
                break;
            case LEFT:
                setImage(MISSILE_LEFT);
                setVelocityX(-SPEED);
                setVelocityY(0);
                break;
            case RIGHT:
                setImage(MISSILE_RIGHT);
                setVelocityX(SPEED);
                setVelocityY(0);
                break;
            default:
                //error
                break;
        }
    }


    @Override
    protected void dealWithCollision(Sprite s) {
        if (!(s instanceof Water || s instanceof Grass) || s instanceof Missile) {
            setHealth(0);
        }
    }

    @Override
    public int compareTo(Sprite o) {
        return 0;
    }
}
