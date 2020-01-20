package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Paddle extends Rectangle {
    private boolean frozen;

    private static final int PADDLE_SPEED = 50;
    private static final String PADDLE_PNG = "Resources/paddle.png";
    private static final double BRICK_WIDTH = GameManager.BRICK_WIDTH;
    private static final double BRICK_HEIGHT = GameManager.BRICK_HEIGHT;

    /**
     * Constructor for the player paddle
     * @param x     the x coordinate for the paddle's upper left
     * @param y     the y coordinate for the paddle's upper left
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public Paddle(double x, double y) throws FileNotFoundException {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.frozen = false;
        this.setFill(new ImagePattern(new Image(new FileInputStream(PADDLE_PNG))));
    }

    /**
     * Accesses whether the paddle is frozen or not
     * @return vulnerable
     */
    public boolean isFrozen() {
        return frozen;
    }

    /**
     * Sets the freeze status of the paddle
     * @param frz   a boolean for whether or not the paddle is frozen
     */
    public void setFreeze(boolean frz) {
        frozen = frz;
    }

    /**
     * Moves the paddle left if unfrozen
     * @param elapsedTime   deltaT
     */
    public void moveLeft(double elapsedTime) {
        if (!isFrozen() && this.getX() > 0) {
            this.setX(Math.max(this.getX() - elapsedTime * PADDLE_SPEED, 0));
        }
    }

    /**
     * Moves the paddle right if unfrozen
     * @param elapsedTime   deltaT
     */
    public void moveRight(double elapsedTime) {
        if (!isFrozen() && this.getX() + this.getWidth() < Main.WIDTH) {
            this.setX(Math.min(this.getX() + elapsedTime * PADDLE_SPEED, Main.WIDTH - this.getWidth()));
        }
    }
}
