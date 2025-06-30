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
 * hit.listeners.BlockRemover is a hit.listeners.HitListener that removes
 * blocks from the game and keeps track of the number of remaining blocks.
 * When a block is hit, this listener will remove the block from the game, decrease the counter of the remaining
 * blocks, and set the ball's color to the block's color.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * Constructs a hit.listeners.BlockRemover.
     *
     * @param game the game from which blocks will be removed.
     * @param remainingBlocks a counter for tracking the number of remaining blocks.
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the event when a block is hit.
     *
     * <p>This method removes the block from the game, removes this listener from the block, decreases the remaining
     * blocks counter by one, and sets the ball's color to the block's color.
     *
     * @param beingHit the block that is hit by the ball.
     * @param hitter the ball that hits the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        hitter.setColor(beingHit.getColor());
    }
}

