package breakout.physics.geometry;

/**
 * A point represents a position in a two dimensional space.
 *
 * @author Melf Kammholz
 */
public class Point {

    private float x;
    private float y;

    /**
     * Creates a point with x- and y-coordinate.
     *
     * @param x A x-coordinate in two dimensional space
     * @param y A y-coordinate in two dimensional space
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a copy of the point that is passed to this constructor.
     *
     * @param point The point to copy
     */
    public Point(Point point) {
        this(point.getX(), point.getY());
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", getX(), getY());
    }

    /**
     * Compares two points by comparing their coordinates.
     *
     * @param point Compare this point with the given point
     * @return Returns {@code true} if the coordinates match, else it returns {@code false}
     */
    public boolean equals(Point point) {
        return getX() == point.getX() && getY() == point.getY();
    }

    /**
     * Gets the x-coordinate of this point.
     *
     * @return The x-coordinate of this point
     */
    public float getX() {
        return x;
    }

    /**
     * Creates a new point with the new x-coordinate.
     *
     * @param x The x-coordinate to set
     */
    public void setX(float x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of this point.
     *
     * @return The y-coordinate of this point
     */
    public float getY() {
        return y;
    }

    /**
     * Creates a new point with the new y-coordinate.
     *
     * @param y The y-coordinate to set
     */
    public void setY(float y) {
        this.y = y;
    }

}
