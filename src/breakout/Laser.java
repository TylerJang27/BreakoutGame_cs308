package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Laser extends Rectangle {

    private static double LASER_WIDTH = Main.WIDTH / 100;
    private static double LASER_HEIGHT = LASER_WIDTH * 10;
    private static double LASER_SPEED = 15;
    private static String LASER_PNG = "Resources/laser.png";

    /**
     * Laser constructor, with random X position
     * @throws FileNotFoundException
     */
    public Laser() throws FileNotFoundException {
        super(Math.random() * (Main.WIDTH - LASER_WIDTH), Main.HEIGHT / 4, LASER_WIDTH, LASER_HEIGHT);
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
