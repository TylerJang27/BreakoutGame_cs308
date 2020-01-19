package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * A class to handle all the following in-game entities:
 *          Ball
 *          Power Up
 */
public class Entity extends Circle {

    private static int RADIUS = Main.SIZE / 20;
    private double xVelocity;
    private double yVelocity;
    private double speed;
    private double direction;

    /**
     * Constructor for Entity, a parent of Ball and PowerUp
     * @param image     Image to fill the Entity
     */
    public Entity (Image image) {
        super(Main.SIZE / 2, Main.SIZE / 2, RADIUS);
        this.setFill(new ImagePattern(image));
    }

    /**
     * Mutator for xVelocity
     * @param vel   the velocity in the x direction
     */
    public void setxVelocity(double vel) {
        xVelocity = vel;
        calcRad();
    }

    /**
     * Mutator for yVelocity
     * @param vel   the velocity in the y direction
     */
    public void setyVelocity(double vel) {
        yVelocity = vel;
        calcRad();
    }

    /**
     * Mutator for direction
     * @param dir   the new direction
     */
    public void setDirection(double dir) {
        direction = dir;
        calcXY();
    }

    /**
     * Mutator for speed
     * @param spd   the new speed
     */
    public void setSpeed(double spd) {
        speed = spd;
        calcXY();
    }

    /**
     * Calculates the speed and direction based on X and Y velocity
     * @return speed    the magnitude of speed
     */
    public double calcSpeed() {
        calcRad();
        return speed;
    }

    /**
     * Calculates the speed and direction based on X and Y velocity
     * @return direction    the direction of travel
     */
    public double calcDirection() {
        calcRad();
        return direction;
    }

    /**
     * Calculates the X and Y velocity based on speed and direction
     * @return xVelocity    the velocity of travel in the horizontal direction
     */
    public double calcX() {
        calcXY();
        return xVelocity;
    }

    /**
     * Calculates the X and Y velocity based on speed and direction
     * @return yVelocity    the velocity of travel in the vertical direction
     */
    public double calcY() {
        calcXY();
        return yVelocity;
    }

    /**
     * Calculates the X and Y velocity based on speed and direction
     */
    public void calcXY() {
        xVelocity = Math.cos(direction) * speed;
        yVelocity = Math.sin(direction) * speed;
    }

    /**
     * Calculates the speed and direction based on X and Y velocity
     */
    public void calcRad() {
        speed = Math.sqrt(xVelocity * xVelocity + yVelocity + yVelocity);
        if (xVelocity == 0) {
            direction = Math.PI / 2;
        } else if (xVelocity < 0) {
            direction = Math.PI - Math.atan(yVelocity / xVelocity);
        } else {
            direction = Math.atan(yVelocity / xVelocity);
        }
    }
}
