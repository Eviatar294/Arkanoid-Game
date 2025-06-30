// 216756650 Eviatar Sayada
package geometry.primitives;

import java.util.List;

/**
 * @author Eviatar Sayada eviatar294@gmail.com
 * @version 1.0
 * @since 2024-07-9
 * Represents a line segment in a 2D coordinate system.*/
public class Line {
    private Point start;
    private Point end;

    /**
     * Constructs a geometry.primitives.Line with the specified start and end points.
     * @param start the start point of the line
     * @param end the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a geometry.primitives.Line with the specified coordinates for the start and end points.
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of the line.
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     * @return the middle point of the line
     */
    public Point middle() {
        return new Point((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point start1 = this.start();
        Point end1 = this.end();
        Point start2 = other.start();
        Point end2 = other.end();

        int o1 = this.orientation(start2);
        int o2 = this.orientation(end2);
        int o3 = other.orientation(start1);
        int o4 = other.orientation(end1);

        // the case when the lines cross each other on one point
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        // cases when 3 points are on the same infinite line
        if (o1 == 0 && this.onLine(start2)) {
            return true;
        }
        if (o2 == 0 && this.onLine(end2)) {
            return true;
        }
        if (o3 == 0 && other.onLine(start1)) {
            return true;
        }
        if (o4 == 0 && other.onLine(end1)) {
            return true;
        }
        return false;
    }

    /**
     * Determines the orientation of the point p with respect to the line segment.
     * @param p the point to check orientation with
     * @return 0 if collinear, 1 if clockwise, 2 if counterclockwise
     */
    private int orientation(Point p) {
        Point start = this.start();
        Point end = this.end();
        //the temp variable is the multiply by crossing of the slopes, and then move all the equation to one side.
        double temp = (start.getY() - p.getY()) * (end.getX() - start.getX())
                - (start.getX() - p.getX()) * (end.getY() - start.getY());
        if (temp == 0) {
            return 0; // collinear
        }
        if (temp > 0) {
            return 1; // clockwise
        }
        return 2; //counterclockwise
    }

    /**
     * Checks if a point lies on the line segment.
     * @param p the point to check
     * @return true if the point lies on the line segment, false otherwise
     */
    private boolean onLine(Point p) {
        Point start = this.start();
        Point end = this.end();
        return p.getX() <= Math.max(start.getX(), end.getX()) && p.getX() >= Math.min(start.getX(), end.getX())
                && p.getY() <= Math.max(start.getY(), end.getY()) && p.getY() >= Math.min(start.getY(), end.getY());
    }

    /**
     * Returns true if this line intersects with both specified lines, false otherwise.
     * @param other1 the first line to check for intersection
     * @param other2 the second line to check for intersection
     * @return true if this line intersects with both specified lines, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     * @param other the other line to find the intersection point with
     * @return the intersection point if the lines intersect, null otherwise
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        boolean isVertical1 = this.start().getX() == this.end().getX();
        boolean isVertical2 = other.start().getX() == other.end().getX();
        if (isVertical1 && isVertical2) {
            double start1 = this.start().getY(), end1 = this.end().getY();
            double start2 = other.start().getY(), end2 = other.end().getY();
            if (start1 == start2) {
                if (end1 > start1 && end2 > start1 || end1 < start1 && end2 < start1) {
                    return null;
                }
                return this.start();
            }
            if (start1 == end2) {
                if (end1 > start1 && start2 > start1 || end1 < start1 && start2 < start1) {
                    return null;
                }
                return this.start();
            }
            if (end1 == end2) {
                if (start1 > end2 && start2 > end2 || start1 < end2 && start2 < end2) {
                    return null;
                }
                return this.end();
            }
            if (end1 == start2) {
                if (start1 > end1 && end2 > end1 || start1 < end1 && end2 < end1) {
                    return null;
                }
                return this.end();
            }
            return null;
        }
        if (isVertical1 || isVertical2) {
            Line vertical = this;
            Line sloping = other;
            if (isVertical2) {
                vertical = other;
                sloping = this;
            }
            double y = sloping.slope() * vertical.start().getX() + sloping.intersectAxisY();
            double x = vertical.start().getX();
            return new Point(x, y);
        }
        if (this.start().getY() == this.end().getY() && other.start().getY() == other.end().getY()) {
            if (this.start.equals(other.start)) {
                return this.start();
            }
            if (this.start.equals(other.end)) {
                return this.start;
            }
            if (this.end.equals(other.start)) {
                return this.end;
            }
            if (this.end.equals(other.end)) {
                return end;
            }
            return null;
        }
        double x = (this.intersectAxisY() - other.intersectAxisY()) / (other.slope() - this.slope());
        double y = (this.slope() * x + this.intersectAxisY());
        return new Point(x, y);
    }

    /**
     * Returns the slope of the line.
     * @return the slope of the line
     */
    private double slope() {
        return (this.start().getY() - this.end().getY()) / (this.start().getX() - this.end().getX());
    }

    /**
     * Returns the y-intercept of the line.
     * @return the y-intercept of the line
     */
    private double intersectAxisY() {
        return this.start().getY() - this.slope() * this.start().getX();
    }

    /**
     * Checks if this line is equal to another line.
     * @param other the other line to check for equality
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start().equals(other.start()) && this.end().equals(other.end())
                || this.end().equals(other.start()) && this.start().equals(other.end());
    }
    /**
     * Returns the closest intersection point between this line and the specified rectangle
     * to the start point of this line. If there are no intersection points, returns null.
     * @param rect the rectangle to check for intersections with this line.
     * @return the closest intersection point to the start of this line, or null if there are no intersection points.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closest = intersectionPoints.get(0);
        double minDistance = this.start.distance(closest);
        for (Point point : intersectionPoints) {
            double distance = point.distance(this.start);
            if (distance < minDistance) {
                minDistance = distance;
                closest = point;
            }
        }
        return closest;
    }
}
