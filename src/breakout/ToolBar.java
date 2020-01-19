package breakout;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to manage and create nodes associated with the top ToolBar
 */
public class ToolBar {

    private static String LIVES_PNG = "Resources/life.png";
    private static String QUIT_PNG = "Resources/quit.png";
    private static String SCORE_PNG = "Resources/score.png";
    private static int MAX_LIFE_DISPLAY = 8;
    private static Font SCORE_FONT = new Font("Stencil", 35);

    private double sceneWidth;
    private double sceneHeight;
    private double barHeight;
    private Rectangle blackBar;
    private List<Node> heartList;
    private ImageView scoreImage;
    private Text scoreText;
    private ImageView quitImage;


    /**
     * Constructor to create a ToolBar object
     * @param width     width of the Scene
     * @param height    height of the Scene
     * @param lives          the number of lives the player has
     * @param score          the player's score
     */
    public ToolBar(double width, double height, int lives, long score) throws FileNotFoundException {
        sceneWidth = width;
        sceneHeight = height;
        barHeight = sceneHeight / 15;

        blackBar = new Rectangle(0, 0, sceneWidth, barHeight);
        blackBar.setFill(Color.BLACK);

        resetHearts(lives);

        scoreImage =  new IconView(new Image(new FileInputStream(SCORE_PNG)), sceneWidth / 2 - sceneWidth / 10, barHeight / 12, sceneWidth / 5, barHeight * 5 / 6, true);

        scoreText = new MenuText(sceneWidth * 11 / 20, barHeight * 5 / 6, "" + score, SCORE_FONT);
        scoreText.setTextAlignment(TextAlignment.LEFT);
        scoreText.setFill(Color.WHITE);

        quitImage = new IconView(new Image(new FileInputStream(QUIT_PNG)), sceneWidth * 15 / 16, barHeight / 12, sceneWidth / 6, barHeight * 5 / 6, true);

        EventHandler<MouseEvent> quitHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent e) {
                //TODO: IMPLMENT RETURN TO MENU PAGE
            }
        };

        quitImage.addEventHandler(MouseEvent.MOUSE_CLICKED, quitHandler);
    }

    /**
     * Creates a List of hearts representing lives
     * @param lives     the number of lives the player has left
     */
    public void resetHearts(int lives) throws FileNotFoundException {
        heartList = new ArrayList<Node>();
        for (int lifeCounter = 0; lifeCounter < lives && lifeCounter < MAX_LIFE_DISPLAY; lifeCounter ++) {
            heartList.add(new IconView(new Image(new FileInputStream(LIVES_PNG)), sceneWidth / 20 * lifeCounter, barHeight / 12, sceneWidth / 20, barHeight * 5 / 6, true));
        }
    }

    /**
     * Sets the value of the score to display
     * @param score     the player's score
     */
    public void setScore(long score) {
        scoreText.setText("" + score);
    }

    /**
     * Returns the List of all ToolBar nodes
     * @return nodeList     List of all Nodes contained in the ToolBar, to be added to a Scene
     */
    public List<Node> getAllNodes() {
        List<Node> nodeList = new ArrayList<Node>();

        nodeList.add(blackBar);
        nodeList.addAll(heartList);
        nodeList.add(scoreImage);
        nodeList.add(scoreText);
        nodeList.add(quitImage);

        return nodeList;
    }
}
