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
    public static final int NUM_POWERUPS = 5;       //-1 signifies no powerup

    private static final int DEFAULT_SPEED = 5;
    private static final Paint BACKGROUND = Main.BACKGROUND;
    private static final int DEFAULT_LIVES = 3;
    private static final int DELAY_TIME = 500;
    public static final int BOUNCE_FACTOR = 5;
    private static final String LOSE_TEXT = "WASTED";
    private static final String WIN_TEXT = "YOU WIN!";

    private double elapsedGameTime;
    private double sceneWidth;
    private double sceneHeight;
    private double centerX;
    private double powerupStart;
    private double freezeStart;
    private double powerupDelay;
    private Scene myScene;
    private ToolBar myToolBar;
    private int lives;
    private long score;
    private int lethality;
    private int myLevel;
    private Paddle paddle;
    private List<Ball> balls;
    private List<Brick> bricks;
    private List<Enemy> enemies;
    private List<Laser> lasers;
    private List<Powerup> powerups;
    private Group root;

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
        initializeSettings();
        myToolBar = new ToolBar(sceneWidth, sceneHeight, lives, score);
        bricks = new ArrayList<Brick>();
        enemies = new ArrayList<Enemy>();
        lasers = new ArrayList<Laser>();
        powerups = new ArrayList<Powerup>();
        paddle = new Paddle(centerX, Main.HEIGHT * 9 / 10);
        root = null;
        populateScene(1);
    }

    /**
     * Resets all game settings, including lives, level, and score
     * @throws FileNotFoundException
     */
    public void initializeSettings() {
        lethality = 1;
        powerupStart = Double.MIN_VALUE;
        freezeStart = Double.MIN_VALUE;
        powerupDelay = 0;
        lives = DEFAULT_LIVES;
        score = 0;
        elapsedGameTime = 0;
    }

    /**
     * Adds an additional life, for use in powerups and cheat codes
     * @throws FileNotFoundException
     */
    public void bonusLife() throws FileNotFoundException {
        lives += 1;
        getToolBar();
        root.getChildren().add(myToolBar.resetHearts(lives));
    }

    /**
     * Deals 1 damage to all hittable elements
     * @throws FileNotFoundException
     */
    public void critHit() throws FileNotFoundException {
        for (Brick br: bricks) {
            br.takeDamage(1);
        }
        for (Enemy e: enemies) {
            e.takeDamage(1);
        }
    }

    /**
     * A handler for all power ups (see cheatcodes)
     * @param powerup   numbers 0 through 4
     * @param time      the duration the powerup should last
     * @throws FileNotFoundException
     */
    public void powerupHandler(int powerup, double time) throws FileNotFoundException {
        if (powerup == 0) {             //shield
            paddle.setFreeze(false);
        } else if (powerup == 1) {      //multiball
            balls.add(new Ball());
            root.getChildren().add(balls.get(balls.size() - 1));
        } else if (powerup == 2) {      //heavy ball
            lethality = 2;
        } else if (powerup == 3) {      //bonus life
            bonusLife();
        }
        powerupStart = elapsedGameTime;
        powerupDelay = time;
    }

    /**
     * Accessor for level
     * @return level    the current game level
     */
    public int getLevel() {
        return myLevel;
    }

    /**
     * Retrieves a List of the nodes comprising the top toolbar
     * @return List of nodes
     * @throws FileNotFoundException
     */
    private List<Node> getToolBar() throws FileNotFoundException {
        myToolBar.setScore(score);
        myToolBar.resetHearts(lives);
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
     * @return balls        the player's balls
     */
    public List<Ball> getBalls() {
        return balls;
    }

    /**
     * Accessor for the paddle
     * @return paddle       the player's paddle
     */
    public Paddle getPaddle() {
        return paddle;
    }

    /**
     * Accessor for elapsedGametime
     * @return elapsedGameTime
     */
    public double getElapsedGameTime() {
        return elapsedGameTime;
    }

    /**
     * Returns the status for whether game play is still alive (not won or lost)
     * @return whether or not game is viable
     */
    private boolean alive() {
        return lives > 0 && myLevel <= 3;
    }

    /**
     * Tests for collision with all objects
     * @throws FileNotFoundException
     */
    private void collision() throws FileNotFoundException {
        wallCollision();
        brickCollision();
        paddleCollision();
        enemyCollision();
        laserCollision();
        powerupCollision();
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
     * Tests for all ball collisions with bricks
     * @throws FileNotFoundException
     */
    private void brickCollision() throws FileNotFoundException {
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
                if (cenX + rad > left && cenX - rad < right) {
                    if (cenY + rad > bot && cenY - rad < top) {
                       b.collideFlatHoriz();
                        destroyBrick(brickCounter, br, cenX, cenY);
                        break;
                    }
                } if (cenY >= bot && cenY <= top) {
                    if (cenX + rad > left && cenX - rad < right) {
                        b.collideFlatVert();
                        destroyBrick(brickCounter, br, cenX, cenY);
                        break;
                    }
                }
            }
        }
    }

    /**
     * Destroys a brick and processes its score
     * @param brickCounter  the index of the brick
     * @param br            a pointer to the brick
     * @param cenX          the x location to spawn powerup
     * @param cenY          the y location to spawn powerup
     * @throws FileNotFoundException
     */
    private void destroyBrick(int brickCounter, Brick br, double cenX, double cenY) throws FileNotFoundException {
        if (br.takeDamage(lethality) == 0) {
            score += 10;
            if (br.getPowerup() >= 0) {
                Powerup p = new Powerup(cenX, cenY, br.getPowerup());
                powerups.add(p);
                root.getChildren().add(p);
            }
            bricks.remove(brickCounter);
            getToolBar();
        }
    }

    /**
     * Tests for all ball collisions with paddles
     */
    private void paddleCollision() {
        for (Ball b: balls) {
            if (b.getCenterX() >= paddle.getX() && b.getCenterX() <= paddle.getX() + paddle.getWidth()) {
                if (b.getCenterY() + b.getRadius() >= paddle.getY()) {
                    b.collideFlatHoriz();
                    double dx = b.getCenterX() - (paddle.getX() + paddle.getWidth() / 2);
                    double dy = b.getCenterY() - paddle.getY();
                    dx /= 4;
                    double xVel = b.getxVelocity() + dx / BOUNCE_FACTOR;
                    double yVel = -1 * Math.abs(b.getyVelocity() - dy / BOUNCE_FACTOR);
                    xVel *= DEFAULT_SPEED / Math.sqrt(xVel * xVel + yVel * yVel);
                    yVel *= DEFAULT_SPEED / Math.sqrt(xVel * xVel + yVel * yVel);
                    b.setxVelocity(xVel);
                    b.setyVelocity(yVel);
                }
            }
        }
    }

    /**
     * Tests for all ball collisions with boss, allowing for scalability
     * @throws FileNotFoundException
     */
    private void enemyCollision() throws FileNotFoundException {
        for (Ball b: balls) {
            for (int enemyCount = enemies.size() - 1; enemyCount >= 0; enemyCount --) {
                Enemy e = enemies.get(enemyCount);
                double distance = Math.sqrt(Math.pow(b.getCenterX() - e.getCenterX(), 2) + Math.pow(b.getCenterY() - e.getCenterY(), 2));
                if (distance < b.getRadius() + e.getRadius()) {
                    b.collideFlatHoriz();
                    b.collideFlatVert();
                    score += 5;
                    getToolBar();
                    if (e.takeDamage(lethality) == 0) {
                        enemies.remove(enemyCount);
                    }
                }
            }
        }
    }

    /**
     * Test for collision between paddle and laser
     */
    private void laserCollision() {
        for (int laserCounter = lasers.size() - 1; laserCounter >= 0; laserCounter --) {
            Laser l = lasers.get(laserCounter);
            if (paddle.getX() <= l.getX() && paddle.getX() + paddle.getWidth() >= l.getX() + l.getWidth()) {
                if (paddle.getY() <= l.getY() + l.getHeight() && paddle.getY() + paddle.getHeight() >= l.getY()) {
                    paddle.setFreeze(true);
                    freezeStart = Math.max(freezeStart, elapsedGameTime);
                }
            }
            if (l.getY() > Main.HEIGHT) {
                lasers.remove(laserCounter);
            }
        }
    }

    /**
     * Tests for collision with powerup, granting effect
     * @throws FileNotFoundException
     */
    private void powerupCollision() throws FileNotFoundException {
        for (int powerupCounter = powerups.size() - 1; powerupCounter >= 0; powerupCounter --) {
            Powerup p = powerups.get(powerupCounter);
            if (p.getCenterX() + p.getRadius() >= paddle.getX() && p.getCenterX() - p.getRadius() <= paddle.getX() + paddle.getWidth()) {
                if (p.getCenterY() + p.getRadius() >= paddle.getY()) {
                    powerupHandler(p.recover(), powerupDelay);
                    powerups.remove(powerupCounter);
                }
            } else if (p.getCenterY() - p.getRadius() >= Main.HEIGHT) {
                powerups.remove(powerupCounter);
            }
        }
    }

    public void launch() {
        for (Ball b: balls) {
            if (!b.getLaunched()) {
                double direction = Math.PI / 2 + (Math.random() - 0.5) / 2;
                b.setxVelocity(Math.cos(direction) * DEFAULT_SPEED);
                b.setyVelocity(Math.sin(direction) * DEFAULT_SPEED);
                b.calcRad();
                b.launch();
            }
        }
    }

    /**
     * Sets the level counter to level
     * @param level     The level to reset to
     * @throws FileNotFoundException
     */ //TODO: IMPLEMENT QUIT
    public void setLevel(int level) throws FileNotFoundException {
        myLevel = level;
        paddle.setX(centerX - paddle.getWidth() / 2);
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
                        bricks.add(new Brick(mark * BRICK_WIDTH, BRICK_HEIGHT * (1.5 + l), hp, bricks.size(), (int) (Math.pow(Math.random(), 10) * NUM_POWERUPS) - 1));
                    }
                }
            }
            if (myLevel == 3) {
                enemies.add(new Enemy());
            }
            populateScene(level);
        }
    }

    /**
     * Creates the game environment, with player, blocks, and all other entities
     * @return Scene with all game elements
     * @throws FileNotFoundException
     */
    public void populateScene(int level) throws FileNotFoundException {
        root = new Group(); //had to make instance in order to add new items (e.g. multiball, update ToolBar)

        balls = new ArrayList<Ball>();
        balls.add(new Ball());

        balls.get(0).setxVelocity(0);
        balls.get(0).setyVelocity(0);

        root.getChildren().addAll(getToolBar());
        root.getChildren().addAll(bricks);
        root.getChildren().addAll(enemies);
        root.getChildren().addAll(balls);
        root.getChildren().add(paddle);
        myScene = new Scene(root, sceneWidth, sceneHeight, BACKGROUND);
    }

    /**
     * Queues the next step for all balls and entities
     * @param elapsedTime   time since last step
     * @throws FileNotFoundException
     */
    public void step(double elapsedTime) throws FileNotFoundException {
        elapsedGameTime += elapsedTime;
        double endTime = 0;

        if (alive()) { //TODO: EXTRACT METHODS AND SHORTEN
            powerupUpdate(elapsedTime);
            freezeUpdate();
            for (Enemy e: enemies) {
                if (Math.round(elapsedGameTime) % 5 == 0) {
                    e.step();
                }
                if (Math.round(elapsedGameTime) % 80 == 0) {
                    Laser l = new Laser();
                    lasers.add(l);
                    root.getChildren().add(l);
                }
            }
            for (Laser l: lasers) {
                l.step(elapsedTime);
            }

            for (Ball b: balls) {
                if (!b.getLaunched()) {
                    b.setCenterX(paddle.getX() + paddle.getWidth() / 2);
                    b.setCenterY(paddle.getY() - paddle.getHeight() / 2);
                } else {
                    b.step(elapsedTime);
                }
            }
            collision();

            if (balls.isEmpty()) {
                lives -= 1;
                populateScene(myLevel);
            }
            if (bricks.isEmpty() && enemies.isEmpty()) {
                myLevel += 1;
                score += 100;
                getToolBar();
                setLevel(myLevel);
                System.out.println(myLevel);
            }
            endTime = elapsedGameTime;

        } else {
            MenuText endText;
            if (lives <= 0) {   //Lose
                endText = new MenuText(Main.WIDTH / 2, Main.HEIGHT / 2, LOSE_TEXT, Main.TITLE_FONT);
            } else {            //Win
                endText = new MenuText(Main.WIDTH / 2, Main.HEIGHT / 2, WIN_TEXT, Main.TITLE_FONT);
            }
            root.getChildren().add(endText);
            if (elapsedGameTime > endTime + DELAY_TIME) {
                //TODO: QUIT
            }
        }
    }

    /**
     * Handles the status of the paddle being frozen
     */
    private void freezeUpdate() {
        if (elapsedGameTime > freezeStart + DELAY_TIME / 2) {
            freezeStart = Double.MIN_VALUE;
            paddle.setFreeze(false);
        }
    }

    /**
     * Handles the status of the powerups and updating their values in step
     * @param elapsedTime   Time since last step
     */
    private void powerupUpdate(double elapsedTime) {
        for (Powerup p: powerups) {
            p.step(elapsedTime);
        }
        if (elapsedGameTime < powerupStart + powerupDelay) {
            paddle.setFreeze(false);
        } else {
            powerupStart = Double.MIN_VALUE;
            lethality = 1;
        }
    }


}
