package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class for the Lasers that the Enemy releases at the player
 * They migrate down with each step
 */
public class Laser extends Rectangle {

    private static double SCENE_WIDTH = Main.WIDTH;
    private static double SCENE_HEIGHT = Main.HEIGHT;
    private static double LASER_WIDTH = SCENE_WIDTH / 100;
    private static double LASER_HEIGHT = LASER_WIDTH * 10;
    private static double LASER_SPEED = 8;
    private static String LASER_PNG = "Resources/laser.png";

    /**
     * Laser constructor, with random X position
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public Laser() throws FileNotFoundException {
        super(Math.random() * (SCENE_WIDTH - LASER_WIDTH), SCENE_HEIGHT / 4, LASER_WIDTH, LASER_HEIGHT);
        this.setFill(new ImagePattern(new Image(new FileInputStream(LASER_PNG))));
    }

    /**
     * A step function that moves the laser down the screen
     * @param elapsedTime
     */
    public void step(double elapsedTime) {
        this.setY(this.getY() + LASER_SPEED * elapsedTime);
    }

}
