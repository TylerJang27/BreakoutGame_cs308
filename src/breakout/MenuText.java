package breakout;

import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * A class to handle standard text on the Main Menu pages, usually within boxes
 */
public class MenuText extends Text{

    /**
     * Constructor to create a MenuText object
     * Used for instances like titleText, startText, etc.
     * @param x     the x coordinate of the center of the text
     * @param y     the y coordinate of the center of the text
     * @param text  the content of the text
     * @param font  the font of the text
     */
    public MenuText(double x, double y, String text, Font font) {
        super(x, y, text);
        this.setFont(font);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setX(this.getX() - this.getLayoutBounds().getWidth() / 2);
    }

}
