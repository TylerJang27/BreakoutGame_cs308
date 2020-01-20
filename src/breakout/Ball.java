package breakout;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class to handle the player's ball(s)
 * Extends Entity, a parent Powerup
 */
public class Ball extends Entity {

    private static String BALL_PNG = "Resources/ball.png";

    private boolean launched;

    /**
     * Constructor for Ball, a child of Entity
     * @param image Image to fill the Ball
     */
    public Ball(Image image) {
        super(image);
        launched = false;
    }

    /**
     * Default constructor for Ball, a child of Entity
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public Ball() throws FileNotFoundException {
        this(new Image(new FileInputStream(BALL_PNG)));
    }

    /**
     * Registers a collision with a flat vertical surface
     */
    public void collideFlatVert() {
        this.setxVelocity(-1 * this.getxVelocity());
    }

    /**
     * Registers a collision with a flat horizontal surface
     */
    public void collideFlatHoriz() {
        this.setyVelocity(-1 * this.getyVelocity());
    }

    /**
     * Sets the value of launched to true
     */
    public void launch() {
        launched = true;
    }

    /**
     * Accesses the value of launched
     * @return launched
     */
    public boolean getLaunched() {
        return launched;
    }

}
