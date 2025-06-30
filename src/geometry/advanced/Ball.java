// 216756650 Eviatar Sayada
package geometry.advanced;
import biuoop.DrawSurface;
import game.CollisionInfo;
import game.Game;
import game.GameEnvironment;
import geometry.primitives.Line;
import geometry.primitives.Point;
import objects_in_game.Sprite;

import java.awt.Color;
/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Ball class represents a ball with a center point, radius, and color.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment gameEnvironment;
    /**
     * Constructs a two.d.shapes.Ball with the specified center point, radius, color and a gameEnvironment.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param ge the gameEnvironment of the ball
     */
    public Ball(Point center, int r, Color color, GameEnvironment ge) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.gameEnvironment = ge;
    }

    /**
     * Constructs a two.d.shapes.Ball with the specified center coordinates, radius, and color.
     *
     * @param x the x-coordinate of the center point
     * @param y the y-coordinate of the center point
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param ge the gameEnvironment of the ball
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment ge) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
        this.gameEnvironment = ge;
    }

    /**
     * Returns the gameEnvironment of the ball.
     *
     * @return the gameEnvironment of the ball
     */
    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * Sets the gameEnvironment of the ball.
     *
     * @param newGameEnvironment the new gameEnvironment of the ball
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }
    /**
     * Returns the center point of the ball.
     *
     * @return the center point of the ball
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Returns the x-coordinate of the center point of the ball.
     *
     * @return the x-coordinate of the center point
     */
    public double getX() {
        return center.getX();
    }

    /**
     * Sets the x-coordinate of the center point of the ball.
     *
     * @param x the new x-coordinate of the center point
     */
    public void setX(double x) {
        center.setX(x);
    }

    /**
     * Returns the y-coordinate of the center point of the ball.
     *
     * @return the y-coordinate of the center point
     */
    public double getY() {
        return center.getY();
    }

    /**
     * Sets the y-coordinate of the center point of the ball.
     *
     * @param y the new y-coordinate of the center point
     */
    public void setY(double y) {
        center.setY(y);
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius of the ball
     */
    public int getSize() {
        return radius;
    }

    /**
     * Returns the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        return color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the surface on which to draw the ball
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) this.getX(), (int) this.getY(), radius);
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the new velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Returns the change in position along the x-axis.
     *
     * @return the change in position along the x-axis
     */
    public double getDx() {
        return velocity.getDx();
    }

    /**
     * Returns the change in position along the y-axis.
     *
     * @return the change in position along the y-axis
     */
    public double getDy() {
        return velocity.getDy();
    }

    /**
     * Moves the ball one step based on its velocity.
     */
    public void moveOneStep() {
        Point endTrajectory = new Point(center.getX() + velocity.getDx(), center.getY() + velocity.getDy());
        Line trajectory = new Line(center, endTrajectory);
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            center = endTrajectory;
            return;
        }
        Point collisionPoint = collisionInfo.collisionPoint();
        Line collisionLine = new Line(this.center, collisionPoint);
        double newX = (center.getX() * radius + collisionPoint.getX()
                * (collisionLine.length() - radius)) / (collisionLine.length());
        double newY = (center.getY() * radius + collisionPoint.getY()
                * (collisionLine.length() - radius)) / (collisionLine.length());
        center = new Point(newX, newY);
        velocity = collisionInfo.collisionObject().hit(this, collisionInfo.collisionPoint(), velocity);
    }

    /**
     * Moves the object forward in time by calling {@link #moveOneStep()}.
     * This method is typically called once per game loop iteration to update the object's state.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Adds the object to the specified game environment.
     *
     * @param g the game environment to add the object to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
    /**
     * Sets the color of this ball.
     *
     * <p>This method assigns a new color to the ball.
     *
     * @param color the new color to be assigned to the ball. It must be an instance of the {@code Color} class.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Removes this ball from the specified game.
     *
     * <p>This method removes the ball from the game's sprite collection, effectively taking the ball out of the game.
     *
     * @param g the game from which the ball will be removed. It must be an instance of the {@code Game} class.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }

}
