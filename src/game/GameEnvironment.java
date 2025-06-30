// 216756650 Eviatar Sayada
package game;
import objects_in_game.Collidable;
import geometry.primitives.Line;
import geometry.primitives.Point;
import geometry.primitives.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The game.GameEnvironment class represents the environment where collisions between
 * collidable objects are managed.
 * It keeps track of all collidable objects and provides methods to add them,
 * retrieve the closest collision information, and access the list of collidables.
 */
public class GameEnvironment {

    private List<Collidable> collidableList;  // List of all collidable objects in the environment

    /**
     * Constructs a new game.GameEnvironment object.
     * Initializes an empty list for collidables.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<>();
    }

    /**
     * Returns the list of collidable objects in the environment.
     *
     * @return the list of collidables
     */
    public List<Collidable> getCollidableList() {
        return this.collidableList;
    }

    /**
     * Adds a collidable object to the environment.
     *
     * @param c the collidable object to add
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Removes a collidable object from the list of collidables in the game.
     *
     * <p>This method removes the specified collidable object from the list of objects that participate in collision
     * detection and handling in the game.
     *
     * @param c the collidable object to be removed from collision handling in the game.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Finds and returns information about the closest collision between a given trajectory
     * and any of the collidable objects in the environment.
     *
     * @param trajectory the trajectory line of an object moving in the environment
     * @return collision information about the closest collision, or null if no collision occurs
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collisionsCollidable = new ArrayList<>();

        // Find all collidables that intersect with the trajectory
        for (Collidable collidable : collidableList) {
            Rectangle rect = collidable.getCollisionRectangle();
            Point point = trajectory.closestIntersectionToStartOfLine(rect);
            if (point != null) {
                collisionsCollidable.add(collidable);
            }
        }

        // If no collisions were found, return null
        if (collisionsCollidable.isEmpty()) {
            return null;
        }

        // Find the closest collision point among all intersecting collidables
        Collidable closestCollidable = collisionsCollidable.get(0);
        Point collisionPoint = trajectory.closestIntersectionToStartOfLine(closestCollidable.getCollisionRectangle());
        double minDistance = trajectory.start().distance(collisionPoint);

        for (Collidable collidable : collisionsCollidable) {
            Point point = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            double distance = trajectory.start().distance(point);
            if (distance < minDistance) {
                closestCollidable = collidable;
                collisionPoint = point;
                minDistance = distance;
            }
        }

        return new CollisionInfo(collisionPoint, closestCollidable);
    }
}
