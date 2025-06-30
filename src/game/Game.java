// 216756650 Eviatar Sayada
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import java.awt.Color;
import java.util.Random;
import hit_listeners.BallRemover;
import hit_listeners.ScoreTrackingListener;
import hit_listeners.BlockRemover;
import objects_in_game.Collidable;
import objects_in_game.Sprite;
import geometry.advanced.Block;
import geometry.advanced.Ball;
import geometry.advanced.Paddle;
import objects_in_game.ScoreIndicator;
import geometry.advanced.Velocity;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The Game class represents a game environment where sprites interact.
 * It manages the game loop, initializes game elements, and handles game logic.
 */
public class Game {

    private SpriteCollection sprites;    // Collection of all sprites in the game
    private GameEnvironment environment; // Environment containing all collidable objects
    private GUI gui; // GUI object for displaying the game
    private Counter numBlocks = new Counter(); // Counter that keeps track of the num of blocks in the game
    private Counter numBalls = new Counter();  // Counter that keeps track of the num of balls in the game
    private Counter score = new Counter();  // Counter that keeps track on the player's score
    private BlockRemover blockRemover = new BlockRemover(this, numBlocks); // a BlockRemover for the game
    private BallRemover ballRemover = new BallRemover(this, numBalls);  // a BallRemover for the game
    private ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(score);  // a ScoreTrackingListener
    private Block deathRegion; // the death region of the game
    private ScoreIndicator scoreIndicator = new ScoreIndicator(score);
    public static final int SCREEN_WIDTH = 800;    // Width of the game screen
    public static final int SCREEN_HEIGHT = 600;   // Height of the game screen
    public static final Color SCREEN_COLOR = new Color(42, 130, 21);
    private static final int SPEED_PADDLE = 5;     // Speed of the paddle
    private static final int SPEED_BALL = 4;      //Speed of the ball
    private static final int NUM_BALLS = 3;      // Number of balls in the game
    private static final int NUM_ROWS = 5;          // Number of rows of blocks

    /**
     * Constructs a new Game object.
     * Initializes the sprite collection, game environment, and GUI.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Adds a sprite to the game.
     *
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * Removes a sprite from the game.
     * @param s the sprite to be removed from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * Removes a collidable from the game.
     *
     * @param c the collidable to be removed from collision handling in the game.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    private void addBlock(Block block) {
        this.numBlocks.increase(1);
    }
    /**
     * Initializes the game by creating blocks, paddle, edges, and balls,
     * and adding them to the game.
     */
    public void initialize() {
        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();
        scoreIndicator.addToGame(this);
        // Create and add the game board
        Block board = new Block(new Rectangle(new Point(0, 30), SCREEN_WIDTH, SCREEN_HEIGHT - 30), SCREEN_COLOR);
        board.addToGame(this);

        // Create and add the paddle
        Paddle paddle = new Paddle(keyboard, new Rectangle(new Point(100, SCREEN_HEIGHT - 30),
                80, 15), SPEED_PADDLE, Color.YELLOW);
        paddle.addToGame(this);

        // Create and add blocks
        Random rand = new Random();
        int y = 150;
        int width = 45;
        int height = 25;
        Color[] colors = new Color[NUM_ROWS];
        colors[0] = Color.GRAY;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;
        colors[4] = Color.WHITE;
        for (int i = NUM_ROWS; i > 0; i--) {
            Color color = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            y += height;
            int x = SCREEN_WIDTH - 30 - width;
            for (int j = 0; j < 5 + i; j++) {
                Block block = new Block(new Rectangle(new Point(x, y), width, height), colors[NUM_ROWS - i]);
                block.addHitListener(blockRemover);
                block.addHitListener(scoreTrackingListener);
                block.addToGame(this);
                addBlock(block);
                x -= width;
            }
        }
        deathRegion = new Block(new Rectangle(new Point(0, SCREEN_HEIGHT - 1),
                SCREEN_WIDTH, 2), SCREEN_COLOR);
        deathRegion.addHitListener(ballRemover);
        deathRegion.addToGame(this);
        // Create and add edges
        Block edge = new Block(new Rectangle(new Point(0, 30), SCREEN_WIDTH, 30), Color.GRAY);
        edge.addToGame(this);
        edge = new Block(new Rectangle(new Point(0, 30), 30, SCREEN_HEIGHT - 30), Color.GRAY);
        edge.addToGame(this);
        edge = new Block(new Rectangle(new Point(SCREEN_WIDTH - 30, 30), 30, SCREEN_HEIGHT - 30), Color.GRAY);
        edge.addToGame(this);

        // Create and add balls
        Ball[] balls = new Ball[NUM_BALLS];
        for (int i = 0; i < balls.length; i++) {
            int angle = rand.nextInt(360);
            int radius = 5;
            int x = 400;
            y = SCREEN_HEIGHT - 100;
            Color color = Color.white;
            balls[i] = new Ball(new Point(x, y), radius, color, environment);
            balls[i].setVelocity(Velocity.fromAngleAndSpeed(angle, SPEED_BALL));
            balls[i].addToGame(this);
        }
        numBalls.increase(NUM_BALLS);
        // Set the balls for the paddle
        paddle.setBalls(balls);
    }

    /**
     * Runs the game animation loop.
     * Manages the animation and timing of the game elements.
     */
    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            if (numBlocks.getValue() == 0) {
                score.increase(100);
                deathRegion.removeHitListener(ballRemover);
                gui.close();
                return;
            }
            if (numBalls.getValue() == 0) {
                deathRegion.removeHitListener(ballRemover);
                gui.close();
                return;
            }
            Sleeper sleeper = new Sleeper();
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * Main method to start the game.
     * Creates a new Game object, initializes it, and starts the game loop.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}

