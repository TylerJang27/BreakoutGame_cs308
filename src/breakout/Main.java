package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;

/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final String TITLE = "Brick Bonanza";
    public static final int SIZE = 600;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.CADETBLUE;

    public static final int WIDTH = SIZE * 2;       //TODO: REFACTOR ALL SCENE HEIGHT, SCENE WIDTH, WIDTH, HEIGHT
    public static final int HEIGHT = SIZE;

    private MenuPage myMenu;
    private GameManager myGameManager;
    private Scene myScene;
    private boolean inGame;


    /**
     * Initialize the scene to start
     * @param stage     Stage on which to show game
     * @throws FileNotFoundException
     */
    @Override
    public void start (Stage stage) throws FileNotFoundException {
        inGame = false;
        myMenu = new MenuPage(WIDTH, HEIGHT);
        myGameManager = new GameManager(WIDTH, HEIGHT);
        myMenu.setMyGameManager(myGameManager);

        myScene = myMenu.getMyScene();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //begins game loop to call step
        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> {
            try {
                step(MILLISECOND_DELAY / 10, stage);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }); //TODO: IMPLEMENT AN ANIMATION
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    //TODO: COMMENTS
    public void startGame() {
        inGame = true;
    }

    //private void handleMouseInput(double x, double y) { }

    /**
     * Updates the scene upon clicks
     * @param elapsedTime   Amount of time since last step
     * @param stage         Stage on which to show game
     * @throws FileNotFoundException
     */
    private void step (double elapsedTime, Stage stage) throws FileNotFoundException {
        if (!inGame) {
            myScene = myMenu.getMyScene();
            if (myScene.equals(myGameManager.getMyScene())) {
                startGame();
            }
        } else {
            myScene = myGameManager.getMyScene();
            myGameManager.step(elapsedTime);
        }
        stage.setScene(myScene);
    }

    /**
     * Handles input for all the cheat codes
     * @param code Keyboard input
     * @throws FileNotFoundException
     */
    private void handleKeyInput (KeyCode code) throws FileNotFoundException {
        if (code == KeyCode.DIGIT8) {
            //Critical Hit

        } else if (code == KeyCode.P) {
            //All Power Ups

        } else if (code == KeyCode.DIGIT1) {
            //Start level 1
            myGameManager.setLevel(1);
            myScene = myGameManager.getMyScene();
        } else if (code == KeyCode.DIGIT2) {
            //Start level 2
            myGameManager.setLevel(2);
            myScene = myGameManager.getMyScene();
        } else if (code == KeyCode.DIGIT3) {
            //Start level 3
            myGameManager.setLevel(3);
            myScene = myGameManager.getMyScene();
        } else if (code == KeyCode.L) {
            //Add extra levels

        } else if (code == KeyCode.R) {
            //Reset to starting position

        } else if (code == KeyCode.SPACE) {


        } else if (code == KeyCode.LEFT) {


        } else if (code == KeyCode.RIGHT) {


        }
    }

    /**
     * Start the game.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
