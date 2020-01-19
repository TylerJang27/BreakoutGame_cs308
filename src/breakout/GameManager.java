package breakout;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to handle all of the scenes and nodes associated with the Game environment
 */
public class GameManager {
    public static final double SIZE = Main.SIZE;

    private static final Paint BACKGROUND = Main.BACKGROUND;
    private static final int DEFAULT_LIVES = 3;
    private static final int BEGINNING_DELAY = 1; //TODO: NO LONGER CONSTANT, ONCE RELEASED

    private double elapsedGameTime;
    private double sceneWidth;
    private double sceneHeight;
    private double centerX;
    private Scene myScene;
    private ToolBar myToolBar;
    private int lives;
    private long score;
    private int myLevel;
    private List<Ball> balls;
    private List<Enemy> bricks;
    private List<Enemy> collidables;

    /**
     * Constructor to create a GameManager object
     * @param width     int width for the entire scene
     * @param height    int height for the entire scene
     * @throws FileNotFoundException
     */
    public GameManager (double width, double height) throws FileNotFoundException {
        sceneWidth = width;
        sceneHeight = height;
        centerX = sceneWidth / 2;
        initializeSettings(1);
        myToolBar = new ToolBar(sceneWidth, sceneHeight, lives, score);
        myScene = setupGame(1);
    }

    /**
     * Resets all game settings, including lives, level, and score
     * @param level     The level to reset to
     */
    public void initializeSettings(int level) {
        lives = DEFAULT_LIVES;
        score = 0;
        elapsedGameTime = 0;
        setLevel(level);
    }

    /**
     * Sets the level counter to level
     * @param level     The level to reset to
     */
    public void setLevel(int level) {
        myLevel = level;
        bricks = new ArrayList<Enemy>(); //TODO: EXPAND TO BE LEVEL INITIALIZER
        collidables = new ArrayList<Enemy>();
        collidables.addAll(bricks);
    }

    /**
     * Retrieves a List of the nodes comprising the top toolbar
     * @return List of nodes
     */
    private List<Node> getToolBar() {
        myToolBar.setScore(score);
        return myToolBar.getAllNodes();
    }

    /**
     * Accessor for the current Scene
     * @return myScene      the current Scene
     */
    public Scene getMyScene() {
        return myScene;
    }

    /**
     * Accessor for the balls
     * @return balls        the player balls
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Creates the game environment, with player, blocks, and all other entities
     * @return Scene with all game elements
     * @throws FileNotFoundException
     */
    private Scene setupGame(int level) throws FileNotFoundException {
        Group root = new Group();

        balls = new ArrayList<Ball>();
        balls.add(new Ball(0));

        //TODO: FINISH

        balls.get(0).setxVelocity(80); //TODO: TEST
        balls.get(0).setyVelocity(50);


        root.getChildren().addAll(getToolBar());
        root.getChildren().addAll(balls);
        return new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

    /**
     * Accessor for elapsedGametime
     * @return elapsedGameTime
     */
    public double getElapsedGameTime() {
        return elapsedGameTime;
    }

    //TODO: COMMENTS
    private boolean started() {
        return elapsedGameTime > BEGINNING_DELAY;
    }

    /**
     * Queues the next step for all balls and entities
     * @param elapsedTime   time since last step
     */
    public void step(double elapsedTime) {
        elapsedGameTime += elapsedTime;
        System.out.println(started());
        System.out.println(elapsedGameTime);

        if (started()) {
            for (Ball b : balls) {
                b.step(elapsedTime, collidables);
            }
        }
        //TODO: add other elements and entities
    }


}
