// 216756650 Eviatar Sayada
package game;
import objects_in_game.Collidable;
import geometry.primitives.Point;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The game.CollisionInfo class represents information about a collision between two objects.
 * It holds the collision point and the object with which the collision occurred.
 */
public class CollisionInfo {

    private Point collisionPoint;       // The point of collision between two objects
    private Collidable collisionObject; // The collidable object involved in the collision

    /**
     * Constructs a game.CollisionInfo object with the specified collision point and collidable object.
     *
     * @param collisionPoint the point at which the collision occurred
     * @param collision the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collision) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collision;
    }

    /**
     * Returns the point at which the collision occurred.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * Returns the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return collisionObject;
    }
}

