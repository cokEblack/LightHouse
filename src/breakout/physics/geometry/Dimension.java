package breakout.physics.geometry;

public class Dimension {

    private float width;
    private float height;

    public Dimension(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public Dimension(Dimension dimension) {
        this(dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public String toString() {
        return String.format("Dimension {width=%.3f, height=%.3f}", getWidth(), getHeight());
    }

    public boolean equals(Dimension dimension) {
        return getWidth() == dimension.getWidth() && getHeight() == dimension.getHeight();
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
