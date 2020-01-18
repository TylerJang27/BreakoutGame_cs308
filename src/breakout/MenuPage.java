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

    public static final String TITLE = "Brick Bonanza"; //TODO: DRY
    public static final int BOX_SIZE = 300;
    public static final int BOX_WIDTH = BOX_SIZE;
    public static final int BOX_HEIGHT = BOX_SIZE / 4;
    public static final Paint BACKGROUND = Color.CADETBLUE; //TODO: DRY
    public static final String START_TEXT = "START";
    public static final String HELP_TEXT = "HELP";

    private int sceneWidth;
    private int sceneHeight;

    /**
     *   Constructor to create a MenuPage object
     *      @param width    the width for the entire scene
     *      @param height   the height for the entire scene
     */
    public MenuPage (int width, int height) {
        sceneWidth = width;
        sceneHeight = height;
    }

    public Scene setupMenu() {
        Group root = new Group();

        Text titleText = new Text(TITLE);                       //TODO: DRY THESE (create a subclass)
        Font titleFont = new Font("", BOX_SIZE / 5);
        titleText.setX(sceneWidth / 2 - BOX_WIDTH * 3 / 5);
        titleText.setY(sceneHeight / 4 + BOX_HEIGHT / 5);
        titleText.setFont(titleFont);

        Rectangle startBox = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
        startBox.setX(sceneWidth / 2 - BOX_WIDTH / 2);
        startBox.setY(sceneHeight / 2 - BOX_HEIGHT / 2);
        startBox.setFill(Color.DARKGREY);

        Text startText = new Text(START_TEXT);
        Font boxFont = new Font("", BOX_SIZE / 10);
        startText.setX(sceneWidth / 2 - BOX_WIDTH / 7.5);
        startText.setY(sceneHeight / 2 + BOX_HEIGHT / 10);
        startText.setFont(boxFont);

        Rectangle helpBox = new Rectangle(BOX_WIDTH, BOX_HEIGHT);
        helpBox.setX(sceneWidth / 2 - BOX_WIDTH / 2);
        helpBox.setY(sceneHeight / 2 + BOX_HEIGHT);
        helpBox.setFill(Color.DARKGREY);

        Text helpText = new Text(HELP_TEXT);
        helpText.setX(sceneWidth / 2 - BOX_WIDTH / 7.5);
        helpText.setY(sceneHeight / 2 + BOX_HEIGHT * 16 / 10);
        helpText.setFont(boxFont);

        EventHandler <MouseEvent> startHandler = new EventHandler<MouseEvent>() {   //TODO: CAN'T BE CLICKED MULTIPLE TIMES, DOESN'T ALLOW CLICK ON TEXT
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
