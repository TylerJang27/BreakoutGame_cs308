package breakout;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class includes the Boss and its implementation
 */
public class Enemy extends Entity {

    private static final int BOSS_MAX_HEALTH = 20;
    private static final String SKIN_PATH = "Resources/dr_brick_";
    private List<Image> skins;
    private int hp;
    private int id;
    private int skinCounter;

    /**
     * Constructor for default Enemy Boss
     * Sets position, skin of boss
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    public Enemy () throws FileNotFoundException {
        super(new Image(new FileInputStream(SKIN_PATH + "1.png")));
        skins = new ArrayList<Image>();
        for (skinCounter = 1; skinCounter <= 4; skinCounter ++) {
            skins.add(new Image(new FileInputStream(SKIN_PATH + skinCounter + ".png")));
        }
        skinCounter = 0;
        hp = BOSS_MAX_HEALTH;
        this.setCenterX(Main.WIDTH / 2);
        this.setCenterY(Main.HEIGHT / 4);
        this.setRadius(Main.WIDTH / 12);
    }

    /**
     * Reduces the hp of the Enemy by damage amount
     * If no hp remaining, return 0 to signify destruction
     * @param damage amount to reduce hp
     * @return 0 for destruction, -1 for no effect
     */
    public int takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            this.setCenterY(Main.HEIGHT * 2);
            return 0;
        }
        return -1;
    }

    /**
     * Animates to change the boss's skin
     */
    public void step() {
        this.setFill(new ImagePattern(skins.get(skinCounter)));
        if (skinCounter >= 3) {
            skinCounter = 0;
        } else {
            skinCounter += 1;
        }
    }
}
