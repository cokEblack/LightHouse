package newton.geometry;

import newton.util.annotation.Immutable;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * This class represents a vector, or rather a displacement in a
 * two dimensional space.
 *
 * @author Melf Kammholz
 */
@Immutable
public class Vector extends Point {

    /**
     * Creates a null vector.
     *
     */
    public Vector() {
        super();
    }

    /**
     * Creates a vector with two coordinates (x- and y- coordinate).
     *
     * @param dx A x-coordinate
     * @param dy A y-coordinate
     */
    public Vector(final float dx, final float dy) {
        super(dx, dy);
    }

    /**
     * Creates a vector by copying the provided vector.
     *
     * @param vector A vector
     */
    public Vector(final Point vector) {
        super(vector);
    }

    /**
     * Creates a new null vector.
     *
     * @return A new null vector
     */
    public static Vector createNullVector() {
        return new Vector();
    }

    @Override
    public Vector setX(float x) {
        return new Vector(x, getY());
    }

    @Override
    public Vector setY(float y) {
        return new Vector(getX(), y);
    }

    /**
     * Adds a displacement, given by two coordinates, to this vector.
     *
     * @param dx A x-coordinate which represents the displacement in x-direction
     * @param dy A y-coordinate which represents the displacement in y-direction
     */
    public Vector add(final float dx, final float dy) {
        return new Vector(getX() + dx, getY() + dy);
    }

    /**
     * Adds a displacement, given by a vector, to this vector.
     *
     * @param vector A displacement given by a vector
     */
    public Vector add(final Point vector) {
        return add(vector.getX(), vector.getY());
    }

    /**
     * Subtracts a displacement, given by two coordinates, from this vector.
     *
     * @param dx A x-coordinate which represents the displacement in x-direction
     * @param dy A y-coordinate which represents the displacement in y-direction
     * @return A new vector
     */
    public Vector subtract(final float dx, final float dy) {
        return new Vector(getX() - dx, getY() - dy);
    }

    /**
     * Subtracts a displacement, given by a vector, from this vector.
     *
     * @param vector A displacement given by a vector
     * @return A new vector
     */
    public Vector subtract(final Point vector) {
        return subtract(vector.getX(), vector.getY());
    }

    /**
     * Scales this vector with a provided scalar.
     *
     * @param scalar A factor by which this vector is scaled
     * @return A new vector
     */
    public Vector scale(final float scalar) {
        return new Vector(getX() * scalar, getY() * scalar);
    }

    /**
     * Inverts this vector (scales the vector with a factor -1).
     *
     * @return A new vector
     */
    public Vector invert() {
        return scale(-1);
    }

    /**
     * Calculates the magnitude of the vector.
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
    public Vector setMagnitude(final float magnitude) {
        return scale(magnitude / getMagnitude());
    }

    /**
     * Normalizes the vector (sets the vector's magnitude equal to 1).
     *
     */
    public Vector normalize() {
        return scale(1 / getMagnitude());
    }

    /**
     * Creates a new vector which is rotated by a given angle around a
     * given point.
     *
     * @param angle The angle to rotate the vector
     * @param point The point the vector is rotated around
     * @return A new vector
     */
    public Vector rotate(float angle, Point point) {

        // Apply 2D rotation matrix
        float x = (float) (getX() * cos(angle) - getY() * sin(angle)) + point.getX();
        float y = (float) (getX() * sin(angle) + getY() * cos(angle)) + point.getY();

        return new Vector(x, y);

    }

    /**
     * Creates a new vector which is rotated by a given angle around the
     * origin.
     *
     * @param angle The angle to rotate the vector
     * @return A new vector
     */
    public Vector rotate(float angle) {
        return rotate(angle, createNullVector());
    }

}
