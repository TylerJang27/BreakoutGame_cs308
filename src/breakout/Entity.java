package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.List;

/**
 * A class to handle all the following in-game entities:
 *          Ball
 *          Power Up
 */
public abstract class Entity extends Circle {

    private static double RADIUS = Main.SIZE / 30;
    private double xVelocity;
    private double yVelocity;
    private double speed;
    private double direction;

    /**
     * Constructor for Entity, a parent of Ball and Powerup
     * @param image     Image to fill the Entity
     */
    public Entity (Image image) {
        this(image, Main.SIZE, Main.SIZE * 3 / 4);
    }

    /**
     * Expanded constructor for Entity, a parent of Ball and Powerup
     * @param image     Image to fill the Entity
     * @param x         Location of centerX
     * @param y         Location of centerY
     */
    public Entity(Image image, double x, double y) {
        super(x, y, RADIUS);
        this.setFill(new ImagePattern(image));
    }

    /**
     * Steps in the established X and Y directions
     * @param elapsedTime   deltaT
     */
    public void step(double elapsedTime) {
        this.setCenterX(this.getCenterX() + xVelocity * elapsedTime);
        this.setCenterY(this.getCenterY() + yVelocity * elapsedTime);
        this.setRotate(this.getRotate() + speed / 40);
    }

    /**
     * Mutator for xVelocity
     * @param vel   the velocity in the x direction
     */
    public void setxVelocity(double vel) {
        xVelocity = vel;
    }

    /**
     * Mutator for yVelocity
     * @param vel   the velocity in the y direction
     */
    public void setyVelocity(double vel) {
        yVelocity = vel;
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
     * Accessor for the y velocity
     * @return xVelocity    the velocity of travel in the horizontal direction
     */
    public double getxVelocity() {
        return xVelocity;
    }

    /**
     * Accessor for the y velocity
     * @return yVelocity    the velocity of travel in the vertical direction
     */
    public double getyVelocity() {
        return yVelocity;
    }

    /**
     * Calculates the speed and direction based on X and Y velocity
     */
    public void calcRad() {
        speed = Math.sqrt(xVelocity * xVelocity + yVelocity * yVelocity);
        if (xVelocity == 0) {
            direction = Math.PI / 2 * yVelocity / Math.abs(yVelocity);
        } else if (xVelocity < 0) {
            direction = Math.PI + Math.atan(yVelocity / xVelocity);
        } else {
            direction = Math.atan(yVelocity / xVelocity);
        }
    }
}
