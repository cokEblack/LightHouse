package newton.geometry;

public class Dimension {

    private float width;
    private float height;

    public Dimension() {
        this(0, 0);
    }

    public Dimension(final float width, final float height) {
        setWidth(width);
        setHeight(height);
    }

    public Dimension(final Dimension dimension) {
        this(dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public String toString() {

        return String.format(
                "%s {width=%.3f, height=%.3f}",
                getClass().getName(),
                getWidth(),
                getHeight()
        );

    }

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

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
