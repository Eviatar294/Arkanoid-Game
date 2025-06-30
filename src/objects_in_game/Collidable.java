// 216756650 Eviatar Sayada
package objects_in_game;

import geometry.advanced.Ball;
import geometry.advanced.Velocity;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Collidable interface represents objects that can be collided with in a game.
 * It provides methods to retrieve the collision shape and handle collisions.
 */
public interface Collidable {

    /**
     * Returns the collision rectangle of the object.
     *
     * @return the collision rectangle representing the shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred with it at a specified collision point
     * and with a given velocity.
     *
     * @param hitter the ball that hit the collidable object
     * @param collisionPoint the point of collision with the object
     * @param currentVelocity the current velocity of the colliding object
     * @return the new velocity expected after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}

