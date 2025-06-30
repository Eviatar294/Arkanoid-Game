// 216756650 Eviatar Sayada
package geometry.primitives;
/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * Represents a point in a 2D coordinate system.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a geometry.primitives.Point with the specified coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if the points have the same coordinates, false otherwise
     */
    public boolean equals(Point other) {
        return this.x == other.x && this.y == other.y;
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return the x-coordinate
     */
    public double getX() {
        return this.x;
    }

    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return the y-coordinate
     */
    public double getY() {
        return this.y;
    }
    /**
     * Returns a string with the values of the point (x,y).
     * @return string of the point coordinates
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
