package game.sprite;


public class Missile extends Sprite {
    private static final String MISSILE_UP = "missile-up.gif";

    private static final String MISSILE_DOWN = "missile-down.gif";
    private static final String MISSILE_LEFT = "missile-left.gif";
    private static final String MISSILE_RIGHT = "missile-right.gif";
    private static final int SPEED = 500;

    public static String getMissileUp() {
        return MISSILE_UP;
    }

    public static String getMissileLeft() {
        return MISSILE_LEFT;
    }

    public static String getMissileDown() {
        return MISSILE_DOWN;
    }

    public static String getMissileRight() {
        return MISSILE_RIGHT;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public Missile(Direction direction, int mask) {
        setBITMASK(mask);
        switch (direction) {
            case UP:
                setImage(getMissileUp());
                setVelocityX(0);
                setVelocityY(-getSPEED());
                break;
            case DOWN:
                setImage(getMissileDown());
                setVelocityX(0);
                setVelocityY(getSPEED());
                break;
            case LEFT:
                setImage(getMissileLeft());
                setVelocityX(-getSPEED());
                setVelocityY(0);
                break;
            case RIGHT:
                setImage(getMissileRight());
                setVelocityX(getSPEED());
                setVelocityY(0);
                break;
            default:
                //error
                break;
        }
    }


    @Override
    protected void dealWithCollision(Sprite s) {

    }

    @Override
    public int compareTo(Sprite o) {
        return 0;
    }
}
