package breakout;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * A class to handle the player's ball(s)
 */
public class Ball extends Entity {

    private static String BALL_PNG = "Resources/ball.png";

    /**
     * Constructor for Ball, a child of Entity
     * @param image Image to fill the Ball
     */
    public Ball(Image image, int id) {
        super(image, id);
    }

    /**
     * Default constructor for Ball, a child of Entity
     * @throws FileNotFoundException
     */
    public Ball(int id) throws FileNotFoundException {
        this(new Image(new FileInputStream(BALL_PNG)), id);
    }

    /**
     * When ball falls off map, remove in GameManager
     * @return id   id for removal
     */
    private int die() {
        return ID;
    }

    /**
     * Tests for collision with any object
     * @param collisionNodes    A list of nodes to test for collision
     */
    public int[] collision(List<Shape> collisionNodes) {
        int[] collisions = {-1, -1};
        if (this.getCenterX() - this.getRadius() <= 0 || this.getCenterX() + this.getRadius() >= Main.WIDTH) {
            collideFlatVert();
        } else if (this.getCenterY() - this.getRadius() <= Main.HEIGHT / 15) {
            collideFlatHoriz();
        } else if (this.getCenterY() + this.getRadius() >= Main.HEIGHT) {
            collisions[0] = die();
        } else if (onPaddle)
        return collisions;
    }

    /**
     * Registers a collision with a flat vertical surface
     */
    public void collideFlatVert() {
        this.setxVelocity(-1 * this.getX());
    }

    /**
     * Registers a collision with a flat horizontal surface
     */
    public void collideFlatHoriz() {
        this.setyVelocity(-1 * this.getY());
    }

}
