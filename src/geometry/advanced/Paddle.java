// 216756650 Eviatar Sayada
package geometry.advanced;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import objects_in_game.Collidable;
import objects_in_game.Sprite;
import java.awt.Color;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Paddle class represents a movable paddle in a game.
 * It implements both the two.d.shapes.Sprite and two.d.shapes.Collidable interfaces.
 * The paddle can move left and right based on user input, and can interact
 * with balls by changing their velocity upon collision.
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;  // Keyboard sensor for user input
    private double dx;  // Movement speed of the paddle
    private Rectangle rect;  // geometry.primitives.Rectangle shape of the paddle
    private Color color;  // Color of the paddle
    private static final int NUM_OF_REIGNS = 5;  // Number of collision regions on the paddle
    private Ball[] balls;  // Array of balls associated with the paddle

    /**
     * Constructs a new two.d.shapes.Paddle object.
     *
     * @param ks    the keyboard sensor used for user input
     * @param rect  the rectangle representing the paddle's shape
     * @param dx    the movement speed of the paddle
     * @param color the color of the paddle
     */
    public Paddle(KeyboardSensor ks, Rectangle rect, double dx, Color color) {
        this.keyboard = ks;
        this.rect = rect;
        this.dx = dx;
        this.color = color;
    }

    /**
     * Sets the balls associated with the paddle.
     *
     * @param balls an array of balls to associate with the paddle
     */
    public void setBalls(Ball[] balls) {
        this.balls = balls;
    }

    /**
     * Moves the paddle to the left by dx units.
     */
    public void moveLeft() {
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        Point newUpperLeft = new Point(upperLeft.getX() - dx, upperLeft.getY());
        this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
    }

    /**
     * Moves the paddle to the right by dx units.
     */
    public void moveRight() {
        Point upperLeft = this.getCollisionRectangle().getUpperLeft();
        Point newUpperLeft = new Point(upperLeft.getX() + dx, upperLeft.getY());
        this.rect = new Rectangle(newUpperLeft, rect.getWidth(), rect.getHeight());
    }

    /**
     * Updates the state of the paddle for each time unit passed.
     * Handles user input for movement and checks for collisions with balls,
     * adjusting their velocity accordingly.
     */
    @Override
    public void timePassed() {
        Point upperLeft = this.rect.getUpperLeft();

        // Move left if left arrow key is pressed
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();

            // Handle edge of screen collision and ball interactions
            if (upperLeft.getX() < 30) {
                this.rect = new Rectangle(new Point(Game.SCREEN_WIDTH - rect.getWidth() - 30, upperLeft.getY()),
                        rect.getWidth(), rect.getHeight());
            }
            for (Ball ball : balls) {
                Point p = ball.getCenter();
                if (rect.isPointInRectangle(p)) {
                    double newX = rect.getUpperLeft().getX() - 1;
                    if (newX <= 30) {
                        ball.setX(31);
                        ball.setY(rect.getUpperLeft().getY() - 1);
                        ball.setVelocity(Math.abs(ball.getDx()), -Math.abs(ball.getDy()));
                        continue;
                    }
                    ball.setX(rect.getUpperLeft().getX() - 1);
                    ball.setVelocity(-Math.abs(ball.getDx()), ball.getDy());
                }
            }
        }

        // Move right if right arrow key is pressed
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();

            // Handle edge of screen collision and ball interactions
            if (upperLeft.getX() + rect.getWidth() > Game.SCREEN_WIDTH - 30) {
                this.rect = new Rectangle(new Point(30, upperLeft.getY()), rect.getWidth(), rect.getHeight());
            }
            for (Ball ball : balls) {
                Point p = ball.getCenter();
                if (rect.isPointInRectangle(p)) {
                    double newX = rect.getUpperLeft().getX() + rect.getWidth() + 1;
                    if (newX >= Game.SCREEN_WIDTH - 31) {
                        ball.setX(Game.SCREEN_WIDTH - 31);
                        ball.setY(rect.getUpperLeft().getY() - 1);
                        ball.setVelocity(-Math.abs(ball.getDx()), -Math.abs(ball.getDy()));
                        continue;
                    }
                    ball.setX(rect.getUpperLeft().getX() + rect.getWidth() + 1);
                    ball.setVelocity(Math.abs(ball.getDx()), ball.getDy());
                }
            }
        }
    }

    /**
     * Draws the paddle on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the paddle
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * Returns the collision rectangle representing the paddle.
     *
     * @return the collision rectangle of the paddle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Responds to a collision with the paddle.
     * Changes the velocity of the colliding object (ball) based on the location of collision.
     *
     * @param collisionPoint  the point of collision with the paddle
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity of the colliding object after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double epsilon = 0.00001;
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < epsilon) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        if (Math.abs(collisionPoint.getY() - (rect.getUpperLeft().getY() + rect.getHeight())) < epsilon) {
            return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        double speed = currentVelocity.getSpeed();
        double upperLeftX = rect.getUpperLeft().getX();
        double regionSize = rect.getWidth() / NUM_OF_REIGNS;
        double angle = -60;

        // Determine the angle of reflection based on collision region
        for (int i = 1; i <= NUM_OF_REIGNS; i++) {
            if (collisionPoint.getX() > upperLeftX + regionSize * i) {
                angle += 30;
            }
        }

        // Calculate and return the new velocity after collision
        Velocity newVelocity;
        if (angle == 0) {
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else {
            newVelocity = Velocity.fromAngleAndSpeed(180 - angle, speed);
        }
        return newVelocity;
    }

    /**
     * Adds the paddle to the given game.
     * Registers the paddle as both a sprite and collidable in the game environment.
     *
     * @param g the game to which the paddle is added
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}
