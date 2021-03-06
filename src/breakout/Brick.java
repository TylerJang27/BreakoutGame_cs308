package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class includes the implementation of all shapes
 */
public class Brick extends Rectangle {

    private static final String HP_3 = "Resources/grey_brick.png";
    private static final String HP_2 = "Resources/red_brick.png";
    private static final String HP_1 = "Resources/blue_brick.png";
    private static final double SCENE_HEIGHT = Main.HEIGHT;
    private static final double BRICK_WIDTH = GameManager.BRICK_WIDTH;
    private static final double BRICK_HEIGHT = GameManager.BRICK_HEIGHT;

    private int hp;
    private int powerup;

    /**
     * Constructor for Brick, taking a default size
     * @param x     The x coordinate of the upper left corner of the brick
     * @param y     The y coordinate of the upper left corner of the brick
     * @param hp    How many hit points the brick has
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public Brick(double x, double y, int hp, int powerup) throws FileNotFoundException {
        super(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        this.hp = hp;
        this.powerup = powerup;
        this.setFill(Color.GREY);
        updateSkin();
    }

    /**
     * Sets the skin for the Brick based on its hp level
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    private void updateSkin() throws FileNotFoundException {
        if (hp == 1) {
            this.setFill(new ImagePattern(new Image(new FileInputStream(HP_1))));
        } else if (hp == 2) {
            this.setFill(new ImagePattern(new Image(new FileInputStream(HP_2))));
        } else {
            this.setFill(new ImagePattern(new Image(new FileInputStream(HP_3))));

        }
    }

    /**
     * Returns the powerup id
     * @return powerup  the powerup id, -1 to 4
     */
    public int getPowerup() {
        return powerup;
    }

    /**
     * Reduces the hp of the Brick by damage
     * @param damage    The amount of hp to deduct
     * @return 0 if destroyed, -1 if not destroyed
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public int takeDamage(int damage) throws FileNotFoundException {
        hp -= damage;
        updateSkin();
        if (hp <= 0) {
            this.setY(SCENE_HEIGHT * 2);
            return 0;
        }
        return -1;
    }
}
