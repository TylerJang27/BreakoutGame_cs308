package breakout;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class for powerups released when certain bricks break
 */
public class Powerup extends Entity {

    private static final String POWERUP_PNG = "Resources/powerup.png";
    private static final int FALL_RATE = 5;

    private int powerupID;

    /**
     * Constructor for powerup
     * @param x     Location of centerX
     * @param y     Location of centerY
     * @param id    Type of powerup
     * @throws FileNotFoundException
     */
    public Powerup(double x, double y, int id) throws FileNotFoundException {
        super(new Image(new FileInputStream(POWERUP_PNG)), x, y);
        powerupID = id;
        this.setxVelocity(0);
        this.setyVelocity(FALL_RATE);
    }

    public int recover() {
        this.setCenterY(Main.HEIGHT * 2);
        return powerupID;
    }

}
