// 216756650 Eviatar Sayada
package hit_listeners;

import geometry.advanced.Ball;
import geometry.advanced.Block;
import game.Counter;
import game.Game;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * hit.listeners.BallRemover is a hit.listeners.HitListener that
 * removes balls from the game and keeps track of the number of remaining balls.
 * When a ball hits a block,
 * this listener will remove the ball from the game and decrease the counter of the remaining balls.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructs a hit.listeners.BallRemover.
     *
     * @param game the game from which balls will be removed.
     * @param remainingBalls a counter for tracking the number of remaining balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Handles the event when a ball hits a block.
     *
     * <p>This method removes the ball from the game and decreases the remaining balls counter by one.
     *
     * @param beingHit the block that is hit by the ball.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        remainingBalls.decrease(1);
    }
}

