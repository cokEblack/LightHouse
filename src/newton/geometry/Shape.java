package newton.geometry;

/**
 * The shape interface describes a few methods which should be available
 * in any implementation.
 *
 * @author Melf Kammholz
 */
public interface Shape {

    /**
     * Returns the bounding rectangle of this shape.
     *
     * @return The bounding rectangle of this shape
     */
    Rectangle getBounds();

    /**
     * Checks if a x- and y-coordinate are contained by the shape.
     *
     * @param x A point's x-coordinate
     * @param y A point's y-coordinate
     * @return Returns {@code true}, if the point is inside the shape.
     */
    boolean contains(float x, float y);

    /**
     * Checks if a point is contained by the shape.
     *
     * @param point A point
     * @return Returns {@code true}, if the point is inside the shape.
     */
    default public boolean contains(Point point) {
        return contains(point.getX(), point.getY());
    }

    /**
     * Checks if a rectangle is completely contained by this shape.
     *
     * @param rectangle A rectangle
     * @return Returns {@code true}, if the rectangle is completely contained
     *     by this shape.
     */
    boolean contains(Rectangle rectangle);

    /**
     * Checks if a circle is completely contained by this shape.
     *
     * @param circle A circle
     * @return Returns {@code true}, if the circle is completely contained
     *     by this shape.
     */
    boolean contains(Circle circle);

    /**
     * Checks if a line is completely contained by this shape.
     *
     * @param line A line
     * @return Returns {@code true}, if the line is completely contained
     *     by this shape.
     */
    boolean contains(Line line);

    /**
     * Checks if a shape is completely contained by this shape.
     *
     * @param shape A shape
     * @return Returns {@code true}, if the shape is completely contained
     *     by this shape.
     * @throws UnsupportedOperationException If the shape implementation is not supported
     *     by this method.
     */
    default public boolean contains(Shape shape) {

        if (shape instanceof Rectangle) {
            return contains((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return contains((Circle) shape);
        }

        if (shape instanceof Line) {
            return contains((Line) shape);
        }

        throw new UnsupportedOperationException("This shape implementation is not supported by this method.");

    }

    /**
     * Checks if a rectangle is intersecting with this shape.
     *
     * @param x The position's x-coordinate
     * @param y The position's y-coordinate
     * @param width The width of a rectangle
     * @param height The height of a rectangle
     * @return Returns {@code true}, if the rectangle is intersecting with this shape
     */
    public boolean intersects(float x, float y, float width, float height);

    /**
     * Checks if a circle is intersecting with this shape.
     *
     * @param cx The center's x-coordinate of a circle
     * @param cy The center's y-coordinate of a circle
     * @param radius The radius of a circle
     * @return Returns {@code true}, if the circle is intersecting with this shape
     */
    public boolean intersects(float cx, float cy, float radius);

    /**
     * Checks if a line is intersecting with this shape.
     *
     * @param line A line
     * @return Returns {@code true}, if the line is intersecting with this shape
     */
    public boolean intersects(Line line);

    /**
     * Checks if a rectangle is intersecting with this shape.
     *
     * @see Shape#intersects(float, float, float, float)
     *
     * @param rectangle A rectangle
     * @return Returns {@code true}, if the rectangle is intersecting with this shape
     */
    default public boolean intersects(Rectangle rectangle) {
        return intersects(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    /**
     * Checks if a circle is intersecting with this shape.
     *
     * @see Shape#intersects(float, float, float)
     *
     * @param circle A circle's center
     * @return Returns {@code true}, if the circle is intersecting with this shape
     */
    default public boolean intersects(Circle circle) {
        return intersects(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
    }

    /**
     * Checks if a shape is intersecting with this shape.
     *
     * @param shape A shape
     * @return Returns {@code true}, if the shape is intersecting with this shape
     * @throws UnsupportedOperationException If the shape implementation is not supported
     *     by this method.
     */
    default public boolean intersects(Shape shape) {

        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        if (shape instanceof Line) {
            return intersects((Line) shape);
        }

        throw new UnsupportedOperationException("This shape implementation is not supported by this method.");

    }

    /**
     * Returns the position of the bounding rectangle.
     *
     * @return The position of the bounding rectangle
     */
    Vector getPosition();

    /**
     * Sets the position of the bounding rectangle.
     *
     * @param x The position's x-coordinate
     * @param y The position's y-coordinate
     */
    void setPosition(float x,  float y);

    /**
     * Sets the position of the bounding rectangle.
     *
     * @see Shape#setPosition(float, float)
     *
     * @param position A position
     */
    default public void setPosition(Point position) {
        setPosition(position.getX(), position.getY());
    }

}
