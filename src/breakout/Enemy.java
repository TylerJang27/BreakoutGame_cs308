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
    private static final String SKIN_PATH = "dr_brick_";
    private List<Image> skins;
    private int hp;
    private int id;
    private int skinCounter;

    public Enemy () throws FileNotFoundException {
        super(new Image(new FileInputStream(SKIN_PATH + "1.png")));
        skins = new ArrayList<Image>();
        for (skinCounter = 1; skinCounter <= 4; skinCounter ++) {
            skins.set(skinCounter - 1, new Image(new FileInputStream(SKIN_PATH + skinCounter + ".png")));
        }
        hp = BOSS_MAX_HEALTH;
        this.setCenterX(Main.WIDTH / 2);
        this.setCenterY(Main.HEIGHT / 4);
        this.setRadius(Main.WIDTH / 3);
    }

    public int takeDamage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            this.setCenterY(Main.HEIGHT * 2);
            return 0;
        }
        return -1;
    }

    public void step() {
        this.setFill(new ImagePattern(skins.get(skinCounter)));
        if (skinCounter >= 4) {
            skinCounter = 0;
        } else {
            skinCounter += 1;
        }
    }
}
