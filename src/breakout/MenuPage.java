package breakout;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * A class to handle all of the scenes associated with the starting Menu Page
 */
public class MenuPage {

    public static final String INSTRUCTIONS_TXT = "Resources/instructions.txt";
    //TODO: FIX PUBLICITY
    private static final String TITLE = Main.TITLE;
    public static final double BOX_SIZE = Main.SIZE / 2;
    public static final double BOX_WIDTH = BOX_SIZE;
    public static final double BOX_HEIGHT = BOX_SIZE / 4;
    private static final Paint BACKGROUND = Main.BACKGROUND;
    private static final String START_TEXT = "START";
    private static final String HELP_TEXT = "HELP";
    private static final String LEVEL_TEXT = "LEVELS";
    private static final Font TITLE_FONT = new Font("Castellar", BOX_WIDTH / 5);
    private static final String BACK_PNG = "Resources/back.png";

    private GameManager myGameManager;
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
     * Sets the value of myGameManager so that levels can be started
     * @param gameManager       The GameManager to be referenced hereafter
     */
    public void setMyGameManager(GameManager gameManager) {
        myGameManager = gameManager;
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
                myGameManager.initializeSettings(1);
                myScene = myGameManager.getMyScene();
            }
        };

        EventHandler <MouseEvent> helpHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myScene = setupHelp();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
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
     * @throws FileNotFoundException
     */
    private Scene setupLevel() throws FileNotFoundException {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 6, LEVEL_TEXT, TITLE_FONT);

        String level = LEVEL_TEXT.substring(0, LEVEL_TEXT.length() - 1) + " ";

        //TODO: LOOPIFY, DRY

        EventHandler <MouseEvent> level1Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                myGameManager.initializeSettings(1);
                myScene = myGameManager.getMyScene();
            }
        };
        EventHandler <MouseEvent> level2Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                myGameManager.initializeSettings(2);
                myScene = myGameManager.getMyScene();
            }
        };
        EventHandler <MouseEvent> level3Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                myGameManager.initializeSettings(3);
                myScene = myGameManager.getMyScene();
            }
        };

        MenuBox level1Box = new MenuBox(centerX, sceneHeight / 4, level + "1");
        MenuBox level2Box = new MenuBox(centerX, sceneHeight / 2, level + "2");
        MenuBox level3Box = new MenuBox(centerX, sceneHeight * 3 / 4, level + "3");

        level1Box.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, level1Handler);
        level2Box.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, level2Handler);
        level3Box.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, level3Handler);


        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream(BACK_PNG)));
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
     * @throws FileNotFoundException
     */
    private Scene setupHelp() throws FileNotFoundException {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 6, HELP_TEXT, TITLE_FONT);

        String instructions = TextReader.readFile(INSTRUCTIONS_TXT);
        MenuText instructionsText = new MenuText(centerX, sceneHeight / 3, instructions, new Font("Calibri", BOX_SIZE / 12));
        instructionsText.setTextAlignment(TextAlignment.CENTER);

        //TODO: ADD ICONS

        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream(BACK_PNG)));
        backButton.addMouseEventHandler(MouseEvent.MOUSE_CLICKED, backHandler);

        root.getChildren().add(titleText);
        root.getChildren().add(instructionsText);
        root.getChildren().addAll(backButton.getAllNodes());
        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

}
