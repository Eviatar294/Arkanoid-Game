package objects_in_game;

import biuoop.DrawSurface;
import game.Counter;
import game.Game;

import java.awt.Color;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * objects.in.game.ScoreIndicator is a sprite that displays the current score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private static final int TEXT_SIZE = 20;

    /**
     * Constructs an objects.in.game.ScoreIndicator with a given score counter.
     *
     * @param score the counter representing the score to be displayed.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * Draws the score indicator on the given draw surface.
     *
     * @param d the draw surface on which to draw the score indicator.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        int width = Game.SCREEN_WIDTH;
        int height = 20;
        d.setColor(Color.BLACK);
        int value = score.getValue();
        String text = "score: " + value;
        d.drawText((width - TEXT_SIZE * text.length()) / 2, (height + TEXT_SIZE) / 2, text, TEXT_SIZE);
    }

    /**
     * Updates the score indicator (currently does nothing).
     */
    public void timePassed() {
        // Currently does nothing for the objects.in.game.ScoreIndicator
    }

    /**
     * Adds the score indicator to the given game.
     *
     * @param g the game to which the score indicator will be added as a sprite.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
