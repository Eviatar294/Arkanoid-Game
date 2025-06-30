// 216756650 Eviatar Sayada
package geometry.advanced;

import geometry.primitives.Point;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The two.d.shapes.Velocity class represents the change in position on the `x` and `y` axes.*/
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a two.d.shapes.Velocity object with the specified changes in x and y directions.
     * @param dx the change in position along the x-axis
     * @param dy the change in position along the y-axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a two.d.shapes.Velocity object from an angle and speed.
     * @param angle the angle of the velocity vector in degrees
     * @param speed the speed (magnitude) of the velocity vector
     * @return a two.d.shapes.Velocity object with the specified angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Applies the velocity to a given point, resulting in a new point.
     * @param p the point to which the velocity is applied
     * @return a new geometry.primitives.Point after applying the velocity to the given point
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }

    /**
     * Returns the change in position along the x-axis.
     * @return the change in position along the x-axis
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the change in position along the y-axis.
     * @return the change in position along the y-axis
     */
    public double getDy() {
        return dy;
    }
    /**
     * Calculates and returns the speed based on the velocity components.
     *
     * @return the speed, which is a double value representing the magnitude of the velocity vector.
     */
    public double getSpeed() {
        return Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
    }

}