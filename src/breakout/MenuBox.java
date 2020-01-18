package breakout;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuBox extends Rectangle {

    /**
     * Constructor to create a MenuBox object
     * Used for instances like startBox, helpBox, etc.
     * @param x         x coordinate of bottom left corner
     * @param y         y coordinate of bottom left corner
     * @param width     width of rectangle
     * @param height    height of rectangle
     */
    public MenuBox(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.setFill(color);
        this.setArcWidth(width / 10);
        this.setArcHeight(height / 10);
    }
}
