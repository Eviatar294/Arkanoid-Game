// 216756650 Eviatar Sayada
package objects_in_game;
import biuoop.DrawSurface;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Sprite interface represents an object that can be drawn on a DrawSurface
 * and can update itself over time.
 */
public interface Sprite {
    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that a unit of time has passed.
     * This method is used to update the sprite's state according to the passage of time.
     */
    void timePassed();
}
