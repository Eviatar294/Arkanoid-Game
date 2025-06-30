// 216756650 Eviatar Sayada
package hit_listeners;

import geometry.advanced.Ball;
import geometry.advanced.Block;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The hit.listeners.HitListener interface should be implemented by classes that listen for hit events in the game.
 * Implementing classes will receive notifications when a block is hit by a ball.
 */
public interface HitListener {

    /**
     * Called when a block is hit by a ball.
     *
     * @param beingHit the block that was hit.
     * @param hitter the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
