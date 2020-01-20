package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
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
    public static final Paint BACKGROUND = Color.CADETBLUE;

    public static final double POWERUP_TIME = 750;
    public static final int WIDTH = SIZE * 2;
    public static final int HEIGHT = SIZE;
    public static final Font TITLE_FONT = new Font("Castellar", MenuPage.BOX_WIDTH / 5);

    private MenuPage myMenu;
    private GameManager myGameManager;
    private Scene myScene;
    private boolean inGame;


    /**
     * Initialize the scene to start
     * @param stage     Stage on which to show game
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    @Override
    public void start (Stage stage) throws FileNotFoundException {
        inGame = false;
        myMenu = new MenuPage(WIDTH, HEIGHT);
        myGameManager = new GameManager(myMenu);
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
        });
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    /**
     * Signifies that the game environment has been entered, stored in inGame
     */
    public void startGame() {
        inGame = true;
    }

    //private void handleMouseInput(double x, double y) { }

    /**
     * Updates the scene upon clicks
     * @param elapsedTime   Amount of time since last step
     * @param stage         Stage on which to show game
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    private void step (double elapsedTime, Stage stage) throws FileNotFoundException {
        /**
         * BUG: once game has ended (through death or quit), only the Start button works
         * Has something to do with how the Scene references are passed around
         */

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

        //adds key input
        if (myScene.getOnKeyPressed() == null) {
            myScene.setOnKeyPressed(e -> {
                try {
                    handleKeyInput(e.getCode(), elapsedTime);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }

    /**
     * Handles input for all the cheat codes
     * @param code Keyboard input
     * @throws FileNotFoundException if file name invalid (see TextReader.java)
     */
    private void handleKeyInput (KeyCode code, double elapsedTime) throws FileNotFoundException {
        if (code == KeyCode.DIGIT8) {
            //Critical Hit
            myGameManager.critHit();
        } else if (code == KeyCode.P) {
            //All Power Ups
            for (int powerNum = 0; powerNum < GameManager.NUM_POWERUPS; powerNum ++) {
                myGameManager.powerupHandler(powerNum, POWERUP_TIME * 50);
            }
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
            //Add extra lives
            myGameManager.bonusLife();
        } else if (code == KeyCode.R) {
            //Reset to starting position
            if (inGame) {
                myGameManager.populateScene(myGameManager.getLevel());
            }
        } else if (code == KeyCode.SPACE) {
            //launches ball
            if (inGame) {
                myGameManager.launch();
            }
        } else if (code == KeyCode.LEFT) {
            //moves paddle left
            myGameManager.getPaddle().moveLeft(elapsedTime);
        } else if (code == KeyCode.RIGHT) {
            //moves paddle right
            myGameManager.getPaddle().moveRight(elapsedTime);
        }
    }

    /**
     * Start the game.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
