package newton.geometry;

/**
 * A container for a width and height.
 *
 * @author Melf Kammholz
 */
public class Dimension {

    /** The width of this dimension */
    private float width;

    /** The height of this dimension */
    private float height;

    /**
     * Creates the dimension with a width of 0 and height of 0.
     *
     */
    public Dimension() {
        this(0, 0);
    }

    /**
     * Creates a dimension with a width and height.
     *
     * @param width The width of this dimension
     * @param height The height of this dimension
     */
    public Dimension(final float width, final float height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * Creates a dimension by copying the provided dimension.
     *
     * @param dimension Copy this dimension
     */
    public Dimension(final Dimension dimension) {
        this(dimension.getWidth(), dimension.getHeight());
    }

    /**
     * Returns a string representation of this dimension.
     *
     * The width and height are fixed to three decimals in this
     * representation.
     *
     * @return A string representation of this dimension
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
     * Two dimensions are equal, if their width and height are equal.
     *
     * If {@code null} is passed to this method, it will be treated
     * as if both dimensions are not equal (thus returning {@code false}).
     *
     * @param dimension A rectangle
     * @return Returns {@code true}, if the width and height are equal, in
     *     any other case return {@code false}.
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
     * Returns the width of this dimension.
     *
     * @return The width of this dimension
     */
    public float getWidth() {
        return width;
    }

    /**
     * Sets the width of this dimension
     *
     * @param width The width to set
     */
    public void setWidth(float width) {
        this.width = width;
    }

    /**
     * Returns the height of this dimension.
     *
     * @return The height of this dimension
     */
    public float getHeight() {
        return height;
    }

    /**
     * Sets the height of this dimension
     *
     * @param height The height to set
     */
    public void setHeight(float height) {
        this.height = height;
    }

}
