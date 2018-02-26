package newton.geometry;

import newton.util.annotation.Immutable;

/**
 * This class represents a point in a two dimensional space.
 *
 * This class uses floats to store the associated x- and
 * y- coordinate.
 *
 * @author Melf Kammholz
 *
 */
@Immutable
public class Point {

    /** The point's x-coordinate */
    private final float x;

    /** The point's y-coordinate */
    private final float y;

    /**
     * Creates a point with the x- and y- coordinate equal to 0.
     *
     */
    public Point() {
        this(0, 0);
    }

    /**
     * Creates a point with a x- and y-coordinate.
     *
     * @param x A x-coordinate
     * @param y A y-coordinate
     */
    public Point(final float x, final float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a point by copying the coordinates of the provided
     * point.
     *
     * @param point A point
     */
    public Point(final Point point) {
        this(point.getX(), point.getY());
    }

    /**
     * Returns a string representation of this point.
     *
     * The x- and y-coordinate are fixed to three decimals in
     * this representation.
     *
     * @return A string representation of this point
     */
    @Override
    public String toString() {
        return String.format(
                "%s {x=%.3f, y=%.3f}",
                getClass().getName(),
                getX(),
                getY()
        );
    }

    /**
     * Checks if two points are equal.
     *
     * Two points are equal, if their coordinates are equal.
     *
     * If {@code null} is passed to this method, it will be treated
     * as if both points are not equal (thus returning {@code false}).
     *
     * @param point A point
     * @return Returns {@code true}, if the coordinates of both points
     *     are equal, in any other case return {@code false}.
     */
    public boolean equals(final Point point) {

        if (point == null) {
            return false;
        }

        if (point == this) {
            return true;
        }

        return getX() == point.getX() && getY() == point.getY();

    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return The x-coordinate of this point
     */
    public float getX() {
        return x;
    }

    /**
     * Creates a new point with a new x-coordinate.
     *
     * @param x A x-coordinate
     * @return A new point with a new x-coordinate
     */
    public Point setX(final float x) {
        return new Point(x, getY());
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return The y-coordinate of this point
     */
    public float getY() {
        return y;
    }

    /**
     * Creates a new point with a new y-coordinate.
     *
     * @param y A y-coordinate
     * @return A new point with a new y-coordinate
     */
    public Point setY(final float y) {
        return new Point(getX(), y);
    }

}
