package newton.geometry;

/**
 * This class holds information about an object's width and height.
 *
 * @author Melf Kammholz
 *
 */
public class Dimension {

    /** The width of an object. */
    private float width;

    /** The height of an object. */
    private float height;

    /**
     * Creates a {@code Dimension} object with the width and height
     * set to 0.
     *
     */
    public Dimension() {
        this(0, 0);
    }

    /**
     * Creates a {@code Dimension} object with a given width and height.
     *
     * @param width The width of the dimension object
     * @param height The height of the dimension object
     */
    public Dimension(final float width, final float height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Creates a {@code Dimension} object by copying the provided
     * {@code Dimension} object.
     *
     * @param dimension The dimension to copy
     */
    public Dimension(final Dimension dimension) {
        this(dimension.getWidth(), dimension.getHeight());
    }

    /**
     * Returns a string representation of this point.
     *
     * The width and height values are fixed to three decimals in
     * this representation.
     *
     * @return A string representation of this point
     */
    @Override
    public String toString() {

        return String.format(
                "%s {width=%.3f, height=%.3f}",
                getClass().getName(),
                getWidth(),
                getHeight()
        );

    }

    /**
     * Checks if two dimensions are equal.
     *
     * Two dimension are equal, if their width and height values are equal.
     *
     * If {@code null} is passed to this method, it will be treated
     * as if both points are not equal (thus returning {@code false}).
     *
     * @param dimension A dimension
     * @return Returns {@code true}, if the  width and height values of both
     *     dimensions are equal, in any other case return {@code false}.
     */
    public boolean equals(Dimension dimension) {

        if (dimension == null) {
            return false;
        }

        if (dimension == this) {
            return true;
        }

        return getWidth() == dimension.getWidth()
                && getHeight() == dimension.getHeight();

    }

    /**
     * Returns the dimension's width.
     *
     * @return The width of this dimension
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets the width of this dimension.
     *
     * @param width The width of this dimension
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Returns the dimension's height.
     *
     * @return The height of this dimension
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the width of this dimension.
     *
     * @param height The width of this dimension
     */
    public void setHeight(float height) {
        this.height = height;
    }

}
