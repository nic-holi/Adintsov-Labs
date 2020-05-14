package game.sprite;

import game.stable.Grass;
import game.stable.Water;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;

import javafx.geometry.Rectangle2D;

import java.util.Objects;


public abstract class Sprite implements Comparable<Sprite> {
    private Image image;
    private double positionX;
    private int health = 1;
    private double positionY;
    private double lastX;
    private double lastY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private int BITMASK;
    private boolean alive = true;

    public Image getImage() {
        return image;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionX() {
        return positionX;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setLastX(double lastX) {
        this.lastX = lastX;
    }

    public double getLastX() {
        return lastX;
    }

    public void setLastY(double lastY) {
        this.lastY = lastY;
    }

    public double getLastY() {
        return lastY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }



    public Sprite() {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        lastX = 0;
        lastY = 0;
    }

    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    public void setImage(String filename) {
        Image i = new Image(getClass().getClassLoader().getResourceAsStream(filename));
        setImage(i);
    }

    public void setPosition(double x, double y) {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y) {
        velocityX = x;
        velocityY = y;
    }


    public void handleCollision(Sprite s) {
        dealWithCollision(s);
        if (health <= 0) {
            setAlive(false);
        }
    }
    public void update(double time) {
        lastX = positionX;
        lastY = positionY;
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void lastPosition() {
        positionX = lastX;
        positionY = lastY;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, positionX, positionY);
    }

    public Rectangle2D getRect() {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s) {
        return s.getRect().intersects(this.getRect());
    }

    @Override
    public int compareTo(Sprite o) {
        if (o instanceof Water) {
            return 1;
        }
        if (o instanceof Grass) {
            return -1;
        }
        if (this instanceof Water) {
            return -1;
        }
        if (this instanceof Grass) {
            return 1;
        }
        return 0;
    }



    protected abstract void dealWithCollision(Sprite s);


    public boolean isAlive() {
        return alive;
    }

    public int getBITMASK() {
        return BITMASK;
    }

    public void setBITMASK(int bITMASK) {
        BITMASK = bITMASK;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}