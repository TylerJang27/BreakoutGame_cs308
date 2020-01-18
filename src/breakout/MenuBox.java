package breakout;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class MenuBox extends Rectangle {

    private static final double BOX_WIDTH = MenuPage.BOX_WIDTH;
    private static final double BOX_HEIGHT = MenuPage.BOX_HEIGHT;
    public static final Font MENU_FONT = new Font("Castellar", BOX_WIDTH / 10);


    private MenuText myText;
    private Glow glow;
    /**
     * Constructor to create a MenuBox object
     * Used for instances like startBox, helpBox, etc.
     * @param x         x coordinate of bottom left corner
     * @param y         y coordinate of bottom left corner
     * @param width     width of rectangle
     * @param height    height of rectangle
     * @param color     color of rectangle
     * @param text      text of button
     */
    public MenuBox(double x, double y, double width, double height, Color color, String text) {
        super(x - width / 2, y, width, height);
        this.setFill(color);
        this.setArcWidth(width / 5);
        this.setArcHeight(height / 2);

        myText = new MenuText(x, y + height * 2 / 3, text, MENU_FONT);

        glow = new Glow();
        glow.setLevel(0.3);

        MenuBox myTarget = this;

        this.addMouseEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                myTarget.setEffect(glow);
            }
        });

        this.addMouseEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
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
     * @param text      text of button
     */
    public MenuBox(double x, double y, String text) {
        this(x, y, BOX_WIDTH, BOX_HEIGHT, Color.DARKGREY, text);
    }

    /**
     * Returns a List that may be added to a root's children
     * @return nodeList, an ArrayList including this and myText
     */
    public List<Node> getAllNodes() {
        List <Node> nodeList = new ArrayList<Node>();
        nodeList.add(this);
        nodeList.add(myText);
        return nodeList;
    }

    /**
     * Adds eventHandler to both this and myText via getAllNodes()
     * Mimics the behavior of Node.addEventHandler()
     * @param eventType a MouseEvent type
     * @param eventHandler a a MouseEvent Handler
     */
    public void addMouseEventHandler(EventType<MouseEvent> eventType, EventHandler<MouseEvent> eventHandler) {
        for (Node myNode: this.getAllNodes()) {
            myNode.addEventHandler(eventType, eventHandler);
        }
    }
}
