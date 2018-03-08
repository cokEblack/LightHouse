package newton.geometry;

import static newton.util.Math.clamp;

/**
 * This class represents a rectangle in a two dimensional space.
 *
 * @author Melf Kammholz
 */
public class Rectangle implements Shape {

    /**
     * The position of this rectangle is the upper left point of the
     * bounding rectangle.
     *
     */
    private Vector position;

    /**
     * The dimension of this rectangle.
     */
    private Dimension dimension;

    /**
     * Creates a rectangle at the position (0, 0) and zero width and
     * height.
     *
     */
    public Rectangle() {
        this(0, 0, 0, 0);
    }

    /**
     * Creates a rectangle at the position (x, y) and a width and height.
     *
     * @param x A x-coordinate
     * @param y A y-coordinate
     * @param width The width of this rectangle
     * @param height The height of this rectangle
     */
    public Rectangle(final float x, final float y, final float width, final float height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    /**
     * Creates a rectangle at a position and a dimension.
     *
     * @param position A position
     * @param dimension A dimension
     */
    public Rectangle(final Point position, final Dimension dimension) {
        setPosition(position);
        setDimension(dimension);
    }

    /**
     * Creates a rectangle by copying the provided rectangle.
     *
     * @param rectangle A rectangle
     */
    public Rectangle(final Rectangle rectangle) {
        this(
                new Vector(rectangle.getPosition()),
                new Dimension(rectangle.getDimension())
        );
    }

    /**
     * Returns a string representation of this rectangle.
     *
     * The x-, y-coordinate, width and height are fixed to three
     * decimals in this representation.
     *
     * @return A string representation of this rectangle
     */
    @Override
    public String toString() {

        return String.format(
                "%s {x=%.3f, y=%.3f, width=%.3f, height=%.3f}",
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );

    }

    /**
     * Checks if two rectangles are equal.
     *
     * Two rectangles are equal, if their position and dimension are equal.
     *
     * If {@code null} is passed to this method, it will be treated
     * as if both rectangles are not equal (thus returning {@code false}).
     *
     * @param rectangle A rectangle
     * @return Returns {@code true}, if the position of both rectangles
     *     are equal and both dimensions are equal, in any other case return
     *     {@code false}.
     */
    public boolean equals(Rectangle rectangle) {

        if (rectangle == null) {
            return false;
        }

        if (rectangle == this) {
            return true;
        }

        return getPosition().equals(rectangle.getPosition())
                && getDimension().equals(rectangle.getDimension());

    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public void setPosition(float x, float y) {
        this.position = new Vector(x, y);
    }

    /**
     * Returns the x-coordinate of this rectangle's position.
     *
     * @return The x-coordinate of this rectangle's position.
     */
    public float getX() {
        return position.getX();
    }

    /**
     * Sets the x-coordinate of this rectangle's position.
     *
     * @param x The x-coordinate of this rectangle's position.
     */
    public void setX(final float x) {
        position = position.setX(x);
    }

    /**
     * Returns the y-coordinate of this rectangle's position.
     *
     * @return The y-coordinate of this rectangle's position.
     */
    public float getY() {
        return position.getY();
    }

    /**
     * Sets the y-coordinate of this rectangle's position.
     *
     * @param y The y-coordinate of this rectangle's position.
     */
    public void setY(final float y) {
        position = position.setY(y);
    }

    /**
     * Returns the rectangle's dimensions.
     *
     * @return The rectangle's dimensions
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Sets the rectangle's dimensions.
     *
     * @param dimension The rectangle's dimensions
     */
    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    /**
     * Returns the width of this rectangle.
     *
     * @return The width of this rectangle
     */
    public float getWidth() {
        return dimension.getWidth();
    }

    /**
     * Sets the width of this rectangle.
     *
     * @param width A width
     */
    public void setWidth(final float width) {
        dimension.setWidth(width);
    }

    /**
     * Returns the height of this rectangle.
     *
     * @return The height of this rectangle
     */
    public float getHeight() {
        return dimension.getHeight();
    }

    /**
     * Sets the height of this rectangle.
     *
     * @param height A height
     */
    public void setHeight(final float height) {
        dimension.setHeight(height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this);
    }

    @Override
    public boolean contains(float x, float y) {

        return getX() <= x && x < getX() + getWidth()
                && getY() <= y && y < getY() + getHeight();

    }

    @Override
    public boolean contains(Rectangle rectangle) {
        return contains(rectangle.getX(), rectangle.getY())
                && contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY())
                && contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight())
                && contains(rectangle.getX(), rectangle.getY() + rectangle.getHeight());
    }

    @Override
    public boolean contains(Circle circle) {

        Point center = circle.getCenter();
        float radius = circle.getRadius();

        return contains(center.getX(), center.getY() - radius)
                && contains(center.getX() + radius, center.getY())
                && contains(center.getX(), center.getY() + radius)
                && contains(center.getX() - radius, center.getY());
    }

    @Override
    public boolean intersects(float cx, float cy, float radius) {

        float dx = cx - clamp(getX(), getX() + getWidth(), cx);
        float dy = cy - clamp(getY(), getY() + getHeight(), cy);

        return dx * dx + dy * dy < radius * radius;

    }

    @Override
    public boolean intersects(float x, float y, float width, float height) {
        return getX() < x + width && getX() + getWidth() > x
                && getY() < y + height && getY() + getHeight() > y;
    }

    @Override
    public boolean contains(Line line) {
        return contains(line.getPosition())
                && contains(line.getPosition().add(line.getDirection()));
    }

    @Override
    public boolean intersects(Line line) {
        return line.intersects(this);
    }

}
