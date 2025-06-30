// 216756650 Eviatar Sayada
package hit_listeners;

import geometry.advanced.Ball;
import geometry.advanced.Block;
import game.Counter;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * hit.listeners.ScoreTrackingListener is a hit.listeners.HitListener that tracks scores when blocks are hit by balls.
 * It increases the score by a fixed amount whenever a block is hit.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a hit.listeners.ScoreTrackingListener with a given score counter.
     *
     * @param scoreCounter the counter representing the current score to be tracked.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Handles the event when a block is hit by a ball.
     *
     * <p>This method increases the score by 5 points and removes this listener from the hit listeners of the block.
     *
     * @param beingHit the block that was hit by the ball.
     * @param hitter the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
