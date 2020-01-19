package breakout;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class to handle all of the scenes and nodes associated with the Game environment
 */
public class GameManager {
    public static final double BRICK_WIDTH = Main.WIDTH / 15;
    public static final double BRICK_HEIGHT = Main.HEIGHT / 15;
    public static final int NUM_POWERUPS = 3;

    private static final double SIZE = Main.SIZE;
    private static final Paint BACKGROUND = Main.BACKGROUND;
    private static final int DEFAULT_LIVES = 3;
    private static final int BEGINNING_DELAY = 100; //TODO: NO LONGER CONSTANT, ONCE RELEASED

    private double elapsedGameTime;
    private double sceneWidth;
    private double sceneHeight;
    private double centerX;
    private Scene myScene;
    private ToolBar myToolBar;
    private int lives;
    private long score;
    private int lethality;
    private int myLevel;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Enemy> enemies;

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
        lethality = 1;
        initializeSettings();
        myToolBar = new ToolBar(sceneWidth, sceneHeight, lives, score);
        bricks = new ArrayList<Brick>();
        enemies = new ArrayList<Enemy>();
        populateScene(1);
    }

    /**
     * Resets all game settings, including lives, level, and score
     * @throws FileNotFoundException
     */
    public void initializeSettings() {
        lives = DEFAULT_LIVES;
        score = 0;
        elapsedGameTime = 0;
    }

    /**
     * Accessor for level
     * @return level    the current game level
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * Sets the level counter to level
     * @param level     The level to reset to
     * @throws FileNotFoundException
     */ //TODO: IMPLEMENT LEVEL =-1 FOR QUIT
    public void setLevel(int level) throws FileNotFoundException {
        myLevel = level;
        bricks = new ArrayList<Brick>();
        enemies = new ArrayList<Enemy>();
        if (myLevel >= 1 && myLevel <= 3) {
            String levelFile = "Resources/level" + myLevel + ".txt";
            String levelContent = TextReader.readFile(levelFile);
            String[] lines = levelContent.split("\n");
            for (int l = 0; l < lines.length; l++) {
                String[] line = lines[l].split(" ");
                for (int mark = 0; mark < line.length; mark++) {
                    int hp = Integer.parseInt(line[mark]);
                    if (hp > 0) {
                        bricks.add(new Brick(mark * BRICK_WIDTH, BRICK_HEIGHT * (1.5 + l), hp, bricks.size(), (int) (Math.random() * NUM_POWERUPS)));
                    }
                }
            }
            //TODO: ADD ENEMY
            populateScene(level);
        }
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
    private void populateScene(int level) throws FileNotFoundException {
        Group root = new Group();

        balls = new ArrayList<Ball>();
        balls.add(new Ball());

        //TODO: FINISH

        balls.get(0).setxVelocity(6); //TODO: TEST
        balls.get(0).setyVelocity(-2);

        root.getChildren().addAll(getToolBar());
        root.getChildren().addAll(bricks);
        root.getChildren().addAll(balls);
        myScene = new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
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
     * Tests for collision with all objects
     * @throws FileNotFoundException
     */
    private void collision() throws FileNotFoundException {
        wallCollision();
        brickbossCollision();
        //TODO: IMPLEMENT PADDLE-BALL COLLISION
        //TODO: IMPLEMENT BALL-BOSS COLLISION
        //TODO: IMPELEMENT POWERUP-PADDLE COLLISION
        //TODO: IMPLEMENT PADDLE-LASER COLLISION
    }

    /**
     * Tests for all ball collisions with walls
     */
    private void wallCollision() {
        for (int ballCounter = balls.size() - 1; ballCounter >= 0; ballCounter --) {
            Ball b = balls.get(ballCounter);
            if (b.getCenterX() - b.getRadius() <= 0 || b.getCenterX() + b.getRadius() >= Main.WIDTH) {
                b.collideFlatVert();
            } else if (b.getCenterY() - b.getRadius() <= Main.HEIGHT / 15) {
                b.collideFlatHoriz();
            } else if (b.getCenterY() + b.getRadius() >= Main.HEIGHT) {
                //ball falls off screen
                b.setCenterY(Main.HEIGHT * 2);
                balls.remove(ballCounter);
            }
        }
    }

    /**
     * Tests for all ball collisions with bricks and boss
     * @throws FileNotFoundException
     */
    private void brickbossCollision() throws FileNotFoundException {
        for (Ball b: balls) {
            for (int brickCounter = bricks.size() - 1; brickCounter >= 0; brickCounter --) {
                Brick br = bricks.get(brickCounter);
                double cenX = b.getCenterX();
                double cenY = b.getCenterY();
                double xVel = b.getxVelocity();
                double yVel = b.getyVelocity();
                double rad = b.getRadius();
                double left = br.getX();
                double right = left + br.getWidth();
                double bot = br.getY();
                double top = bot + br.getHeight();
                /*System.out.println("brC \t" + brickCounter +
                        "\ncenX \t" + cenX +
                        "\ncenY \t" + cenY +
                        "\nrad \t" + rad +
                        "\nleft \t" + left +
                        "\nright \t" + right +
                        "\nbot \t" + bot +
                        "\ntop \t" + top);*/
                if (cenX + rad > left && cenX - rad < right) {
                    if (cenY + rad > bot && cenY - rad < top) {
                       b.collideFlatHoriz();
                       if (br.takeDamage(lethality) ==  0) {
                           score += 10;
                       }
                       break;
                    }
                } if (cenY >= bot && cenY <= top) {
                    if (cenX + rad > left && cenX - rad < right) {
                        b.collideFlatVert();
                        if (br.takeDamage(lethality) ==  0) {
                            score += 10;
                        }
                        break;
                    }
                }


            }
        }
    }

    /**
     * Queues the next step for all balls and entities
     * @param elapsedTime   time since last step
     * @throws FileNotFoundException
     */
    public void step(double elapsedTime) throws FileNotFoundException {
        elapsedGameTime += elapsedTime;

        if (started()) {
            for (Ball b : balls) {
                b.step(elapsedTime);
            }
            collision();
        }
        //TODO: add other elements and entities
    }


}
