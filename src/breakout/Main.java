package breakout;
import breakout.MenuPage;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Feel free to completely change this code or delete it entirely. 
 */
public class Main extends Application {

    public static final String TITLE = "Brick Bonanza";
    public static final int SIZE = 600;
    public static final int WIDTH = SIZE * 2;
    public static final int HEIGHT = SIZE;
    public static final int FRAMES_PER_SECOND = 60;
    public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final Paint BACKGROUND = Color.CADETBLUE;

    private Scene myScene;


    /**
     * Initialize the scene to start
     */
    @Override
    public void start (Stage stage) {
        MenuPage menu = new MenuPage(WIDTH, HEIGHT); //TODO: SHOULD BE STATIC?

        myScene = menu.setupMenu();
        stage.setScene(myScene);
        stage.setTitle(TITLE);
        stage.show();
        //KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY)); //TODO: IMPLEMENT AN ANIMATION
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        //animation.getKeyFrames().add(frame);
        animation.play();
    }

    //public void setupGame() { } //TODO: FIGURE OUT

    //private void handleMouseInput(double x, double y) { }


    /**
     * Start the game.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
