package breakout;
import javafx.event.EventHandler;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class MenuBox extends Rectangle {

    private static final double BOX_WIDTH = MenuPage.BOX_WIDTH;
    private static final double BOX_HEIGHT = MenuPage.BOX_HEIGHT;


    private Glow glow;
    /**
     * Constructor to create a MenuBox object
     * Used for instances like startBox, helpBox, etc.
     * @param x         x coordinate of bottom left corner
     * @param y         y coordinate of bottom left corner
     * @param width     width of rectangle
     * @param height    height of rectangle
     * @param color     color of rectangle
     */
    public MenuBox(double x, double y, double width, double height, Color color) {
        super(x - width / 2, y, width, height);
        this.setFill(color);
        this.setArcWidth(width / 5);
        this.setArcHeight(height / 2);

        glow = new Glow();
        glow.setLevel(0.3);

        MenuBox myTarget = this;
        this.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                myTarget.setEffect(glow);
            }
        });

        this.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                myTarget.setEffect(null);
            }
        });
    }

    /**
     * Constructor to create a MenuBox object
     * Used for instances like startBox, helpBox, etc.
     * @param x         x coordinate of bottom left corner
     * @param y         y coordinate of bottom left corner
     * @param color     color of rectangle
     */
    public MenuBox(double x, double y) {
        this(x, y, BOX_WIDTH, BOX_HEIGHT, Color.DARKGREY);
    }

}
