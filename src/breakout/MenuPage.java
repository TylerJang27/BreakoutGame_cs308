package breakout;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MenuPage {

    //TODO: FIX PUBLICITY
    public static final String TITLE = "Brick Bonanza"; //TODO: DRY
    public static final double BOX_SIZE = 300;
    public static final double BOX_WIDTH = BOX_SIZE;
    public static final double BOX_HEIGHT = BOX_SIZE / 4;
    public static final Paint BACKGROUND = Color.CADETBLUE; //TODO: DRY
    public static final String START_TEXT = "START";
    public static final String HELP_TEXT = "HELP";
    public static final String LEVEL_TEXT = "LEVELS";
    public static final Font TITLE_FONT = new Font("Castellar", BOX_WIDTH / 5);

    private double sceneWidth;
    private double sceneHeight;
    private double centerX;
    private Scene myScene;
    private EventHandler<MouseEvent> backHandler;

    /**
     * Constructor to create a MenuPage object
     * @param width    int width for the entire scene
     * @param height   int height for the entire scene
     */
    public MenuPage (double width, double height) {
        sceneWidth = width;
        sceneHeight = height;
        centerX = sceneWidth / 2;
        myScene = setupMenu();
        backHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                myScene = setupMenu();
            }
        };
    }

    /**
     * Accessor for the current Scene
     * @return myScene, the current Scene
     */
    public Scene getMyScene() {
        return myScene;
    }

    /**
     * Creates the Main Menu page, with title and three buttons
     * Has buttons to go to Level 1, Help screen, and any Level
     * @return Scene of the Main Menu page
     */
    private Scene setupMenu() {

        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 4 + BOX_HEIGHT / 5, TITLE, TITLE_FONT);

        MenuBox startBox = new MenuBox(centerX, sceneHeight / 2 - BOX_HEIGHT / 2, START_TEXT);
        MenuBox helpBox = new MenuBox(centerX, sceneHeight / 2 + BOX_HEIGHT, HELP_TEXT);
        MenuBox levelBox = new MenuBox(centerX, sceneHeight / 2 + BOX_HEIGHT * 5 / 2, LEVEL_TEXT);

        EventHandler <MouseEvent> startHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                /*if (!root.getChildren().contains(helpBox)) {
                    root.getChildren().addAll(helpBox.getAllNodes());
                }*/

            }
        };

        EventHandler <MouseEvent> helpHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                myScene = setupHelp();
            }
        };

        EventHandler <MouseEvent> levelHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myScene = setupLevel();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        startBox.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, startHandler);
        helpBox.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, helpHandler);
        levelBox.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, levelHandler);

        //TODO: DRY
        root.getChildren().add(titleText);
        root.getChildren().addAll(startBox.getAllNodes());
        root.getChildren().addAll(helpBox.getAllNodes());
        root.getChildren().addAll(levelBox.getAllNodes());

        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

    /**
     * Creates the Levels page, with Levels and back button
     * @return Scene of the Levels page
     */
    private Scene setupLevel() throws FileNotFoundException {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 8, LEVEL_TEXT, TITLE_FONT);

        String level = LEVEL_TEXT.substring(0, LEVEL_TEXT.length() - 1) + " ";

        //TODO: LOOPIFY, DRY
        MenuBox level1Box = new MenuBox(centerX, sceneHeight / 4, level + "1");
        MenuBox level2Box = new MenuBox(centerX, sceneHeight / 2, level + "2");
        MenuBox level3Box = new MenuBox(centerX, sceneHeight * 3 / 4, level + "3");

        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream("Resources/back.png")));
        backButton.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, backHandler);

        //TODO: DRY
        root.getChildren().add(titleText);
        root.getChildren().addAll(level1Box.getAllNodes());
        root.getChildren().addAll(level2Box.getAllNodes());
        root.getChildren().addAll(level3Box.getAllNodes());
        root.getChildren().addAll(backButton.getAllNodes());

        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

    /**
     * Creates the Help page, with description and back button
     * @return Scene of the Help page
     */
    private Scene setupHelp() {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 8, HELP_TEXT, TITLE_FONT);

        
        //TODO: ADD ICONS

        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream("Resources/back.png")));
        backButton.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, backHandler);

        root.getChildren().add(titleText);
        root.getChildren().addAll(backButton.getAllNodes());
        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

}
