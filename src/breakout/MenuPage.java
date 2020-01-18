package breakout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;

public class MenuPage {

    //TODO: FIX PUBLICITY
    public static final String TITLE = "Brick Bonanza"; //TODO: DRY
    public static final double BOX_SIZE = 300;
    public static final double BOX_WIDTH = BOX_SIZE;
    public static final double BOX_HEIGHT = BOX_SIZE / 4;
    public static final Paint BACKGROUND = Color.CADETBLUE; //TODO: DRY
    public static final String START_TEXT = "START";
    public static final String HELP_TEXT = "HELP";
    public static final Font MENU_FONT = new Font("Castellar", BOX_SIZE / 10);
    public static final Font TITLE_FONT = new Font("Castellar", BOX_SIZE / 5);

    private double sceneWidth;
    private double sceneHeight;
    private double centerX;

    /**
     *   Constructor to create a MenuPage object
     *      @param width    int width for the entire scene
     *      @param height   int height for the entire scene
     */
    public MenuPage (double width, double height) {
        sceneWidth = width;
        sceneHeight = height;
        centerX = sceneWidth / 2;
    }

    public Scene setupMenu() {

        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 4 + BOX_HEIGHT / 5, TITLE, TITLE_FONT);

        MenuBox startBox = new MenuBox(centerX, sceneHeight / 2 - BOX_HEIGHT / 2);
        MenuText startText = new MenuText(centerX, sceneHeight / 2 + BOX_HEIGHT / 10, START_TEXT, MENU_FONT);

        MenuBox helpBox = new MenuBox(centerX, sceneHeight / 2 + BOX_HEIGHT);
        MenuText helpText = new MenuText(centerX, sceneHeight / 2 + BOX_HEIGHT * 16 / 10, HELP_TEXT, MENU_FONT);

        EventHandler <MouseEvent> startHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                if (!root.getChildren().contains(helpBox)) {
                    root.getChildren().add(helpBox);
                    root.getChildren().add(helpText);
                }
            }
        };

        startBox.addEventFilter(MouseEvent.MOUSE_CLICKED, startHandler);
        startText.addEventFilter(MouseEvent.MOUSE_CLICKED, startHandler);

        root.getChildren().add(titleText);
        root.getChildren().add(startBox);
        root.getChildren().add(startText);

        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

}
