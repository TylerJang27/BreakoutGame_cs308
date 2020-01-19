package breakout;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class to handle the player's ball(s)
 */
public class Ball extends Entity {

    private static String BALL_PNG = "Resources/ball.png";

    /**
     * Constructor for Ball, a child of Entity
     * @param image Image to fill the Ball
     */
    public Ball(Image image) {
        super(image);
    }

    /**
     * Default constructor for Ball, a child of Entity
     * @throws FileNotFoundException
     */
    public Ball() throws FileNotFoundException {
        this(new Image(new FileInputStream(BALL_PNG)));
    }



}
