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

    public static final double BOX_SIZE = Main.SIZE / 2;
    public static final double BOX_WIDTH = BOX_SIZE;
    public static final double BOX_HEIGHT = BOX_SIZE / 4;

    private static final String INSTRUCTIONS_TXT = "Resources/instructions.txt";
    private static final String TITLE = Main.TITLE;
    private static final Paint BACKGROUND = Main.BACKGROUND;
    private static final String START_TEXT = "START";
    private static final String HELP_TEXT = "HELP";
    private static final String LEVEL_TEXT = "LEVELS";
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
     * Sets myScene to be the main menu
     * Also acts as a quit feature
     * @return a generated scene with the main menu
     */
    public Scene setupMenu() {

        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 4 + BOX_HEIGHT / 5, TITLE, Main.TITLE_FONT);

        //on clicking start, start level1
        EventHandler <MouseEvent> startHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myGameManager.setLevel(1);
                    myScene = myGameManager.getMyScene();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        //on clicking help, build help scene
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

        //on clicking level, build level scene
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

        MenuBox startBox = new MenuBox(centerX, sceneHeight / 2 - BOX_HEIGHT / 2, START_TEXT, startHandler);
        MenuBox helpBox = new MenuBox(centerX, sceneHeight / 2 + BOX_HEIGHT, HELP_TEXT, helpHandler);
        MenuBox levelBox = new MenuBox(centerX, sceneHeight / 2 + BOX_HEIGHT * 5 / 2, LEVEL_TEXT, levelHandler);

        root.getChildren().add(titleText);
        root.getChildren().addAll(startBox.getAllNodes());
        root.getChildren().addAll(helpBox.getAllNodes());
        root.getChildren().addAll(levelBox.getAllNodes());

        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

    /**
     * Creates the Levels page, with Levels and back button
     * @return Scene of the Levels page
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    private Scene setupLevel() throws FileNotFoundException {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 6, LEVEL_TEXT, Main.TITLE_FONT);

        String level = LEVEL_TEXT.substring(0, LEVEL_TEXT.length() - 1) + " ";

        //on clicking level N, start level N
        EventHandler <MouseEvent> level1Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myGameManager.setLevel(1);
                    myScene = myGameManager.getMyScene();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        EventHandler <MouseEvent> level2Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myGameManager.setLevel(2);
                    myScene = myGameManager.getMyScene();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };
        EventHandler <MouseEvent> level3Handler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                try {
                    myGameManager.setLevel(3);
                    myScene = myGameManager.getMyScene();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        };

        MenuBox level1Box = new MenuBox(centerX, sceneHeight / 4, level + "1", level1Handler);
        MenuBox level2Box = new MenuBox(centerX, sceneHeight / 2, level + "2", level2Handler);
        MenuBox level3Box = new MenuBox(centerX, sceneHeight * 3 / 4, level + "3", level3Handler);

        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream(BACK_PNG)), backHandler);

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
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    private Scene setupHelp() throws FileNotFoundException {
        Group root = new Group();

        MenuText titleText = new MenuText(centerX, sceneHeight / 6, HELP_TEXT, Main.TITLE_FONT);

        String instructions = TextReader.readFile(INSTRUCTIONS_TXT);
        MenuText instructionsText = new MenuText(centerX, sceneHeight / 3, instructions, new Font("Calibri", BOX_SIZE / 12));
        instructionsText.setTextAlignment(TextAlignment.CENTER);

        MenuBox backButton = new MenuBox(sceneWidth / 10, sceneHeight / 10, BOX_HEIGHT, BOX_HEIGHT, Color.DARKGREY, "", new Image(new FileInputStream(BACK_PNG)), backHandler);

        root.getChildren().add(titleText);
        root.getChildren().add(instructionsText);
        root.getChildren().addAll(backButton.getAllNodes());
        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

}
