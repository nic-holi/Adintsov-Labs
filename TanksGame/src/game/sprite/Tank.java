package game.sprite;
import java.util.ArrayList;

import game.main.Game;
import game.stable.Grass;


public abstract class Tank extends Sprite {

    private static final long MISSILE_DELAY = 500000000;

    public static int SPEED = 550;
    private String TANK_UP;
    private String TANK_DOWN;
    private String TANK_LEFT;
    private String TANK_RIGHT;

    private Direction direction = Direction.UP;
    public Direction missileDirection = getDirection();
    private long fireTime = System.nanoTime();

    private ArrayList<Sprite> elements;

    public void setTANK_UP(String TANK_UP) { this.TANK_UP = TANK_UP; }

    public void setTANK_DOWN(String TANK_DOWN) { this.TANK_DOWN = TANK_DOWN; }

    public void setTANK_LEFT(String TANK_LEFT) { this.TANK_LEFT = TANK_LEFT; }

    public void setTANK_RIGHT(String TANK_RIGHT) { this.TANK_RIGHT = TANK_RIGHT; }

    public String getTANK_UP() { return TANK_UP;   }

    public String getTANK_DOWN() { return TANK_DOWN;    }

    public String getTANK_LEFT() { return TANK_LEFT;    }

    public String getTANK_RIGHT() { return TANK_RIGHT;    }


    public Tank(ArrayList<Sprite> elements) {
        this.elements = elements;
        setYellow();
        setImage(TANK_UP);
        setBITMASK(Game.ENEMY_TANK_MASK); //default, enemy tanks
    }

    public Tank(ArrayList<Sprite> elements, int mask) {
        this(elements);
        setBITMASK(mask);
    }

    public void update(double time) {
        setSpeedWithDirection();
        setImageWithDirection();
        super.update(time);
    }

    public void setDirection(Direction dir) {
        this.direction = dir;
    }

    private void setSpeedWithDirection() {
        switch (getDirection()) {
            case RIGHT:
                setVelocityX(SPEED);
                setVelocityY(0);
                break;
            case LEFT:
                setVelocityX(-SPEED);
                setVelocityY(0);
                break;
            case UP:
                setVelocityX(0);
                setVelocityY(-SPEED);
                break;
            case DOWN:
                setVelocityX(0);
                setVelocityY(SPEED);
                break;
            default:
                setVelocityX(0);
                setVelocityY(0);
                break;
        }
    }

    private void setImageWithDirection() {
        switch (missileDirection) {
            case RIGHT:
                setImage(getTANK_RIGHT());
                break;
            case LEFT:
                setImage(getTANK_LEFT());
                break;
            case UP:
                setImage(getTANK_UP());
                break;
            case DOWN:
                setImage(getTANK_DOWN());
                break;
            default:
                break;
        }
    }

    public Missile fireMissile() {
        long time = System.nanoTime();
        if (time - fireTime < MISSILE_DELAY) return null;
        Missile missile = new Missile(missileDirection, getMissileMask());
        switch (missileDirection) {
            case UP:
                missile.setPosition(getPositionX() + 0.5 * getWidth() - 0.5 * missile.getWidth(), getPositionY() - missile.getHeight());
                break;
            case DOWN:
                missile.setPosition(getPositionX() + 0.5 * getWidth() - 0.5 * missile.getWidth(), getPositionY() + getHeight());
                break;
            case LEFT:
                missile.setPosition(getPositionX() - missile.getWidth(), getPositionY() + 0.5 * getHeight() - 0.5 * missile.getHeight());
                break;
            case RIGHT:
                missile.setPosition(getPositionX() + getWidth(), getPositionY() + 0.5 * getHeight() - 0.5 * missile.getHeight());
                break;
            default:
                //error
                break;
        }
        fireTime = time;
        if (missile != null) elements.add(missile);
        return missile;
    }

    public void handleCollision(Sprite s) {
        if (getBITMASK() == s.getBITMASK()) {
            lastPosition();
        }
        else if (s.getBITMASK() == Game.STABLE_MASK
                && !(s instanceof Grass)) {
            lastPosition();
        }
        super.handleCollision(s);
    }


    public abstract int getMissileMask();


    public void setGreen() {
        setTANK_UP(GREEN_TANK_UP);
        setTANK_DOWN(GREEN_TANK_DOWN);
        setTANK_LEFT(GREEN_TANK_LEFT) ;
        setTANK_RIGHT(GREEN_TANK_RIGHT);
    }


    public void setRed() {
        setTANK_UP(RED_TANK_UP);
        setTANK_DOWN(RED_TANK_DOWN);
        setTANK_LEFT(RED_TANK_LEFT) ;
        setTANK_RIGHT(RED_TANK_RIGHT);
    }


    public void setYellow() {
        setTANK_UP(YELLOW_TANK_UP);
        setTANK_DOWN(YELLOW_TANK_DOWN);
        setTANK_LEFT(YELLOW_TANK_LEFT) ;
        setTANK_RIGHT(YELLOW_TANK_RIGHT);
    }


    public Direction getDirection() {
        return direction;
    }

    //constants

    public static final String GREEN_TANK_UP = "green-tank-up.gif";
    public static final String GREEN_TANK_DOWN = "green-tank-down.gif";
    public static final String GREEN_TANK_LEFT = "green-tank-left.gif";
    public static final String GREEN_TANK_RIGHT = "green-tank-right.gif";
    public static final String RED_TANK_UP = "red-tank-up.gif";
    public static final String RED_TANK_DOWN = "red-tank-down.gif";
    public static final String RED_TANK_LEFT = "red-tank-left.gif";
    public static final String RED_TANK_RIGHT = "red-tank-right.gif";
    public static final String YELLOW_TANK_UP = "yellow-tank-up.gif";
    public static final String YELLOW_TANK_DOWN = "yellow-tank-down.gif";
    public static final String YELLOW_TANK_LEFT = "yellow-tank-left.gif";
    public static final String YELLOW_TANK_RIGHT = "yellow-tank-right.gif";
}
