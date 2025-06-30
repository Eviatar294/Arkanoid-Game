// 216756650 Eviatar Sayada
package geometry.advanced;
import biuoop.DrawSurface;
import game.Game;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;
import hit_listeners.HitListener;
import hit_listeners.HitNotifier;
import objects_in_game.Collidable;
import objects_in_game.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Block class represents a rectangular block with collision properties.
 * It implements both the {@link Collidable} and {@link Sprite} interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;  // The rectangular shape of the block
    private Color color;     // The color of the block
    private List<HitListener> hitListeners;


    /**
     * Constructs a two.d.shapes.Block with the specified rectangle and color.
     *
     * @param rect the rectangle defining the boundaries of the block
     * @param color the color of the block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        hitListeners = new ArrayList<>();
    }

    /**
     * Returns the collision rectangle of the block.
     *
     * @return the collision rectangle of the block
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns a new velocity after a collision with the block.
     * Determines the new velocity based on the collision point and current velocity.
     *
     * @param hitter the ball that hit the collidable object
     * @param collisionPoint the point of collision with the block
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity after collision
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double epsilon = 0.0001;

        // Handle horizontal collisions
        if (Math.abs(collisionPoint.getX() - rect.getUpperLeft().getX()) < epsilon
                || Math.abs(collisionPoint.getX() - (rect.getUpperLeft().getX() + rect.getWidth())) < epsilon) {
            dx = -dx;
        }

        // Handle vertical collisions
        if (Math.abs(collisionPoint.getY() - rect.getUpperLeft().getY()) < epsilon
                || Math.abs(collisionPoint.getY() - (rect.getUpperLeft().getY() + rect.getHeight())) < epsilon) {
            dy = -dy;
        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }


    /**
     * Draws the block on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the block
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
    }

    /**
     * Does nothing in this implementation.
     * This method is required by the two.d.shapes.Sprite interface but has no effect for this class.
     */
    public void timePassed() {
        // This method is intentionally left blank
    }

    /**
     * Adds the block to the specified game.
     * Registers the block as both a sprite and a collidable object in the game.
     *
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /**
     * Removes this block from the specified game.
     *
     * <p>This method removes the block from both the game's sprite collection and collidable collection.
     *
     * @param g the game from which the block will be removed.
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    /**
     * Checks if the color of this block matches the color of the given ball.
     *
     * @param ball the ball whose color is to be compared with this block's color.
     * @return {@code true} if the colors match, {@code false} otherwise.
     */
    public boolean ballColorMatch(Ball ball) {
        return this.color == ball.getColor();
    }

    /**
     * Notifies all registered hit listeners about a hit event.
     *
     * <p>This method makes a copy of the current hit listeners and iterates over them to notify each one
     * about the hit event.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Removes a hit listener from the list of listeners for hit events.
     *
     * @param hl the hit listener to be removed.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Adds a hit listener to the list of listeners for hit events.
     *
     * @param hl the hit listener to be added.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Returns the color of this block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return this.color;
    }

}
