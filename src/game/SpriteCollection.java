// 216756650 Eviatar Sayada
package game;
import objects_in_game.Sprite;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The game.SpriteCollection class represents a collection of sprites in a game.
 * It manages adding sprites, updating their states over time, and drawing them on a DrawSurface.
 */
public class SpriteCollection {
    private List<Sprite> listSprites;

    /**
     * Constructs a new game.SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.listSprites = new ArrayList<>();
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added
     */
    public void addSprite(Sprite s) {
        listSprites.add(s);
    }

    /**
     * Removes a sprite from the list of sprites.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        listSprites.remove(s);
    }

    /**
     * Notifies all sprites in the collection that a unit of time has passed.
     * This method updates the state of all sprites according to the passage of time.
     */
    public void notifyAllTimePassed() {
        List<Sprite> copySprites = new ArrayList<>(listSprites);
        for (Sprite s : copySprites) {
            s.timePassed();
        }
    }

    /**
     * Draws all sprites in the collection on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprites
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : listSprites) {
            d.setColor(Color.BLACK); // Setting default color before drawing each sprite
            s.drawOn(d);
        }
    }
}
