package breakout.physics.geometry;

import static java.lang.Math.sqrt;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * A vector represents a displacement in a two dimensional space.
 *
 * @author Melf Kammholz
 */
public class Vector extends Point {

    /**
     * TODO consider removing this shorthand field and add a factory method as this field is mutable
     */
    public static final Vector NULL_VECTOR = new Vector(0, 0);

    /**
     * Creates a vector with a x- and y- coordinate.
     *
     * @param x A x-coordinate in two dimensional space
     * @param y A y-coordinate in two dimensional space
     */
    public Vector(float x, float y) {
        super(x, y);
    }

    /**
     * Creates a copy of the vector that is passed to this constructor.
     *
     * @param vector The vector (or point) to copy
     */
    public Vector(Point vector) {
        super(vector);
    }

    /**
     * Creates a new vector with the added displacement.
     *
     * @param dx The x-coordinate which represents the displacement in
     *           x-direction.
     * @param dy The y-coordinate which represents the displacement in
     *           y-direction.
     */
    public void add(float dx, float dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    /**
     * Creates a new vector with the added displacement.
     *
     * @param vector A vector to add
     */
    public void add(Point vector) {
        add(vector.getX(), vector.getY());
    }

    /**
     * Creates a new vector with the subtracted displacement.
     *
     * @param dx The x-coordinate which represents the displacement in
     *           x-direction.
     * @param dy The y-coordinate which represents the displacement in
     *           y-direction.
     */
    public void subtract(float dx, float dy) {
        add(-1 * dx, -1 * dy);
    }

    /**
     * Creates a new vector with the subtracted displacement.
     *
     * @param vector A vector to subtract
     * @return A new vector with the subtracted displacement
     */
    public void subtract(Point vector) {
        subtract(vector.getX(), vector.getY());
    }

    /**
     * Creates a new vector with the scaled displacement.
     *
     * @param scalar A factor by which the new vector is scaled
     */
    public void scale(float scalar) {
        setX(getX() * scalar);
        setY(getY() * scalar);
    }

    /**
     * Returns the magnitude of the vector.
     *
     * @return The magnitude of the vector.
     */
    public float getMagnitude() {
        return (float) sqrt(getX() * getX() + getY() * getY());
    }

    /**
     * Sets the magnitude of the vector by normalizing and scaling
     * the vector by the provided factor.
     *
     * @param magnitude The magnitude to set
     */
    public void setMagnitude(final float magnitude) {
        scale(magnitude / getMagnitude());
    }

    /**
     * Normalizes the vector (sets the vector's magnitude equal to 1).
     *
     */
    public void normalize() {
        scale(1 / getMagnitude());
    }

    /**
     * Creates a new vector which is rotated by a given angle around a
     * given point.
     *
     * @param angle The angle to rotate the vector
     * @param point The point the vector is rotated around
     */
    public void rotate(float angle, Point point) {

        // Apply 2D rotation matrix
        float x = (float) (getX() * cos(angle) - getY() * sin(angle)) + point.getX();
        float y = (float) (getX() * sin(angle) + getY() * cos(angle)) + point.getY();

        setX(x);
        setY(y);

    }

    /**
     * Creates a new vector which is rotated by a given angle around the
     * origin.
     *
     * @param angle The angle to rotate the vector
     * @return A new vector which is rotated
     */
    public void rotate(float angle) {
        rotate(angle, Vector.NULL_VECTOR);
    }

}
