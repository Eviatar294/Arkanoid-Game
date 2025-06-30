// 216756650 Eviatar Sayada
package geometry.primitives;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * The geometry.primitives.Rectangle class represents a rectangle in a 2D space.
 * It is defined by an upper-left point, width, and height.
 */
public class Rectangle {
    private Point upperLeft; // The upper-left corner of the rectangle
    private double width;    // The width of the rectangle
    private double height;   // The height of the rectangle

    /**
     * Constructs a new rectangle with the specified upper-left corner,
     * width, and height.
     *
     * @param upperLeft the upper-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Returns a list of intersection points of the rectangle with a given line.
     * If the line does not intersect with the rectangle, an empty list is returned.
     *
     * @param line the line to find intersection points with
     * @return a list of intersection points with the rectangle
     */
    public List<Point> intersectionPoints(Line line) {
        List<Point> list = new ArrayList<>();
        Point upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        Point downRight = new Point(upperLeft.getX() + width, upperLeft.getY() + height);
        Line horizontalUp = new Line(upperLeft, upperRight);
        Line horizontalDown = new Line(downLeft, downRight);
        Line verticalLeft = new Line(upperLeft, downLeft);
        Line verticalRight = new Line(upperRight, downRight);

        if (line.intersectionWith(horizontalUp) != null) {
            Point point = line.intersectionWith(horizontalUp);
            list.add(point);
        }
        if (line.intersectionWith(horizontalDown) != null) {
            Point point = line.intersectionWith(horizontalDown);
            list.add(point);
        }
        if (line.intersectionWith(verticalLeft) != null) {
            Point point = line.intersectionWith(verticalLeft);
            list.add(point);
        }
        if (line.intersectionWith(verticalRight) != null) {
            Point point = line.intersectionWith(verticalRight);
            list.add(point);
        }
        return list;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left corner of the rectangle.
     *
     * @return the upper-left corner of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Draws the rectangle on a given DrawSurface with the specified color.
     *
     * @param surface the DrawSurface on which to draw the rectangle
     * @param color   the color of the rectangle
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(),
                (int) width, (int) height);
    }

    /**
     * Checks if a given point is inside the rectangle.
     *
     * @param p the point to check
     * @return true if the point is inside the rectangle, false otherwise
     */
    public boolean isPointInRectangle(Point p) {
        return p.getX() >= upperLeft.getX() && p.getX() <= upperLeft.getX() + width
                && p.getY() >= upperLeft.getY() && p.getY() <= upperLeft.getY() + height;
    }
}
