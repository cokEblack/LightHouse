package newton.geometry;

import static java.lang.Math.sqrt;
import static newton.util.Math.clamp;

/**
 * This class represents a circle in a two dimensional space.
 *
 * @author Melf Kammholz
 */
public class Circle implements Shape {

    /**
     * The position of this circle is the upper left point of the
     * bounding rectangle.
     *
     * Do not confuse the position with the center of the circle.
     *
     */
    private Vector position;

    /** The radius of this circle. */
    private float radius;

    /**
     * Creates a circle at the position (0,0) with a radius of 0.
     */
    public Circle() {
        this(0, 0, 0);
    }

    /**
     * Creates a circle at the position (x, y) with a radius.
     *
     * @param x The x position of the bounding rectangle
     * @param y The y position of the bounding rectangle
     * @param radius The radius of this circle
     */
    public Circle(final float x, final float y, final float radius) {
        this(new Point(x, y), radius);
    }

    /**
     * Creates a circle at a given position and given radius.
     *
     * @param position The position of the bounding rectangle
     * @param radius The radius of this circle
     */
    public Circle(final Point position, final float radius) {
        setPosition(position);
        setRadius(radius);
    }

    /**
     * Creates a circle by using the values of the given circle, which
     * essentially copies the given circle.
     *
     * @param circle A circle which should be copied
     */
    public Circle(final Circle circle) {
        this(new Point(circle.getPosition()), circle.getRadius());
    }

    /**
     * Returns a string representation of this circle.
     *
     * The x-, y-, cx- and cy-coordinate and radius are fixed to three
     * decimals in this representation.
     *
     * @return A string representation of this point
     */
    @Override
    public String toString() {

        return String.format(
                "%s {x=%.3f, y=%.3f, cx=%.3f, cy=%.3f, radius=%.3f}",
                getClass().getName(),
                getX(),
                getY(),
                getCenterX(),
                getCenterY(),
                getRadius()
        );

    }

    /**
     * Checks if two circles are equal.
     *
     * Two circles are equal, if their coordinates and radii are equal.
     *
     * If {@code null} is passed to this method, it will be treated
     * as if both circles are not equal (thus returning {@code false}).
     *
     * @param circle A circle
     * @return Returns {@code true}, if the coordinates of both points
     *     are equal and both radii are equal, in any other case return
     *     {@code false}.
     */
    public boolean equals(Circle circle) {

        if (circle == null) {
            return false;
        }

        if (circle == this) {
            return true;
        }

        return getPosition().equals(circle.getPosition())
                && getRadius() == circle.getRadius();

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
     * Returns the x-coordinate of this circle's position.
     *
     * @return The x-coordinate of this circle's position.
     */
    public float getX() {
        return position.getX();
    }

    /**
     * Sets the x-coordinate of this circle's position.
     *
     * @param x The x-coordinate of this circle's position.
     */
    public void setX(final float x) {
        position = position.setX(x);
    }

    /**
     * Returns the y-coordinate of this circle's position.
     *
     * @return The y-coordinate of this circle's position.
     */
    public float getY() {
        return position.getY();
    }

    /**
     * Sets the y-coordinate of this circle's position.
     *
     * @param y The y-coordinate of this circle's position.
     */
    public void setY(final float y) {
        position = position.setY(y);
    }

    /**
     * Returns the center's x-coordinate.
     *
     * @return A x-coordinate to set as the center's x-coordinate
     */
    public float getCenterX() {
        return position.getX() + getRadius();
    }

    /**
     * Sets the center's x-coordinate.
     *
     */
    public void setCenterX(final float cx) {
        setX(cx - getRadius());
    }

    /**
     * Returns the center's y-coordinate.
     *
     * @return The center's y-coordinate.
     */
    public float getCenterY() {
        return position.getY() + getRadius();
    }

    /**
     * Sets the center's y-coordinate.
     *
     */
    public void setCenterY(final float cy) {
        setY(cy - getRadius());
    }

    /**
     * Returns the center of this circle as a {@code Point}.
     *
     */
    public Vector getCenter() {
        return new Vector(getCenterX(), getCenterY());
    }

    /**
     * Sets the center of this circle.
     *
     * @param center A new point to set as the circle's center
     */
    public void setCenter(Point center) {
        setCenterX(center.getX());
        setCenterY(center.getY());
    }

    /**
     * Returns the radius of this circle.
     *
     * @return The radius of this circle
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets the radius of this circle.
     *
     * @param radius A radius
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 2 * getRadius(), 2 * getRadius());
    }

    @Override
    public boolean contains(float x, float y) {

        // Distance of each coordinate
        float dx = x - getCenterX();
        float dy = y - getCenterY();

        // The distance of the point to the center is shorter than the radius
        return dx * dx + dy * dy <= getRadius() * getRadius();

    }

    @Override
    public boolean contains(Rectangle rectangle) {

        // Each vertex of the rectangle has to be inside the circle
        return contains(rectangle.getX(), rectangle.getY())
                && contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY())
                && contains(rectangle.getX() + rectangle.getWidth(), rectangle.getY() + rectangle.getHeight())
                && contains(rectangle.getX(), rectangle.getY() + rectangle.getHeight());

    }

    @Override
    public boolean contains(Circle circle) {

        // Distance of the centers
        float dx = getCenterX() - circle.getCenterX();
        float dy = getCenterY() - circle.getCenterY();
        float distance = (float) sqrt(dx * dx + dy * dy);

        // If the radius of this circle is greater than the distance and the
        // other circle's radius, the other circle is completely inside this
        // circle
        return getRadius() > distance + circle.getRadius();

    }

    @Override
    public boolean contains(Line line) {

        // The starting and ending point of the line segment are contained by the
        // circle
        return contains(line.getPosition())
                && contains(line.getPosition().add(line.getDirection()));

    }

    @Override
    public boolean intersects(float cx, float cy, float radius) {

        // Distance of the centers
        float dx = getCenterX() - cx;
        float dy = getCenterY() - cy;

        // Two circles intersect if the distance of both centers is shorter
        // than the added radii.
        return dx * dx + dy * dy <= (getRadius() + radius) * (getRadius() + radius);

    }

    @Override
    public boolean intersects(float x, float y, float width, float height) {

        // Distance of the closest point on the rectangle's borders to the
        // circle's center
        float dx = getCenterX() - clamp(x, x + width, getCenterX());
        float dy = getCenterY() - clamp(y, y + height, getCenterY());

        // ... if the distance is shorter than the radius of the circle, the
        // rectangle intersects with this circle.
        return dx * dx + dy * dy < getRadius() * getRadius();

    }

    @Override
    public boolean intersects(Line line) {
        return line.intersects(this);
    }

}
