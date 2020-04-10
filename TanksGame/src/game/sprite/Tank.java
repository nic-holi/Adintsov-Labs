package game.sprite;
import java.util.ArrayList;

import game.ui.Game;


public abstract class Tank extends Sprite {

    private static final long MISSILE_DELAY = 500000000;

    public static long getMissileDelay() {
        return MISSILE_DELAY;
    }

    private int SPEED = 550;

    public void setSPEED(int SPEED) {
        this.SPEED = SPEED;
    }

    public int getSPEED() {
        return SPEED;
    }

    private String TANK_UP;

    public void setTANK_UP(String TANK_UP) {
        this.TANK_UP = TANK_UP;
    }

    public String getTANK_UP() {
        return TANK_UP;
    }

    private String TANK_DOWN;

    public void setTANK_DOWN(String TANK_DOWN) {
        this.TANK_DOWN = TANK_DOWN;
    }

    public String getTANK_DOWN() {
        return TANK_DOWN;
    }

    private String TANK_LEFT;

    public void setTANK_LEFT(String TANK_LEFT) {
        this.TANK_LEFT = TANK_LEFT;
    }

    public String getTANK_LEFT() {
        return TANK_LEFT;
    }

    private String TANK_RIGHT;

    public void setTANK_RIGHT(String TANK_RIGHT) {
        this.TANK_RIGHT = TANK_RIGHT;
    }

    public String getTANK_RIGHT() {
        return TANK_RIGHT;
    }

    private Direction direction = Direction.UP;

    private Direction missileDirection = getDirection();

    public Direction getMissileDirection() {
        return missileDirection;
    }

    public void setMissileDirection(Direction missileDirection) {
        this.missileDirection = missileDirection;
    }

    private long fireTime = System.nanoTime();

    private ArrayList<Sprite> elements;

    public Tank(ArrayList<Sprite> elements) {
        this.elements = elements;
        setWhite();
        setImage(TANK_UP);

    }

    public Tank(ArrayList<Sprite> elements, int mask) {
        this(elements);
        setBITMASK(mask) ;
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
                setVelocityX(getSPEED());
                setVelocityY(0);
                break;
            case LEFT:
                setVelocityX(-getSPEED());
                setVelocityY(0);
                break;
            case UP:
                setVelocityX(0);
                setVelocityY(-getSPEED());
                break;
            case DOWN:
                setVelocityX(0);
                setVelocityY(getSPEED());
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



    public abstract int getMissileMask();


    public void setWhite() {
        TANK_UP = WHITE_TANK_UP;
        TANK_DOWN = WHITE_TANK_DOWN;
        TANK_LEFT = WHITE_TANK_LEFT;
        TANK_RIGHT = WHITE_TANK_RIGHT;
    }


    public Direction getDirection() {
        return direction;
    }

    //constants
    private static final String WHITE_TANK_UP = "/graphic/white-tank-up.gif";
    private static final String WHITE_TANK_DOWN = "/graphic/white-tank-down.gif";
    private static final String WHITE_TANK_LEFT = "/graphic/white-tank-left.gif";
    private static final String WHITE_TANK_RIGHT = "/graphic/white-tank-right.gif";
    private static final String GREEN_TANK_UP = "/graphic/green-tank-up.gif";
    private static final String GREEN_TANK_DOWN = "/graphic/green-tank-down.gif";
    private static final String GREEN_TANK_LEFT = "/graphic/green-tank-left.gif";
    private static final String GREEN_TANK_RIGHT = "/graphic/green-tank-right.gif";
    private static final String RED_TANK_UP = "/graphic/red-tank-up.gif";
    private static final String RED_TANK_DOWN = "/graphic/red-tank-down.gif";
    private static final String RED_TANK_LEFT = "/graphic/red-tank-left.gif";
    private static final String RED_TANK_RIGHT = "/graphic/red-tank-right.gif";
    private static final String YELLOW_TANK_UP = "/graphic/yellow-tank-up.gif";
    private static final String YELLOW_TANK_DOWN = "/graphic/yellow-tank-down.gif";
    private static final String YELLOW_TANK_LEFT = "/graphic/yellow-tank-left.gif";
    private static final String YELLOW_TANK_RIGHT = "/graphic/yellow-tank-right.gif";

}
