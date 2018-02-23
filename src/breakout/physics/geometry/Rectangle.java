package breakout.physics.geometry;

public class Rectangle implements Shape {

    private Vector position;
    private Dimension dimension;

    public Rectangle(Point position, Dimension dimension) {
        this.position = new Vector(position);
        this.dimension = dimension;
    }

    public Rectangle(float x, float y, float width, float height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    public Rectangle(Rectangle rectangle) {
        this(
                rectangle.getX(),
                rectangle.getY(),
                rectangle.getWidth(),
                rectangle.getHeight()
        );
    }

    /**
     * Returns a {@code String} representation of this {@code Rectangle}.
     *
     * @return A {@code String} representation of this {@code Rectangle}.
     */
    @Override
    public String toString() {
        return String.format(
                "Rectangle {x=%.3f, y=%.3f, width=%.3f, height=%.3f}",
                getX(),
                getY(),
                getWidth(),
                getHeight()
        );
    }

    public boolean equals(Rectangle rectangle) {
        return getPosition().equals(rectangle.getPosition()) && getDimension().equals(rectangle.getDimension());
    }

    @Override
    public float getX() {
        return position.getX();
    }

    @Override
    public void setX(float x) {
        position.setX(x);
    }

    @Override
    public float getY() {
        return position.getY();
    }

    @Override
    public void setY(float y) {
        position.setY(y);
    }

    @Override
    public Vector getPosition() {
        return position;
    }

    @Override
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    @Override
    public void setPosition(Point point) {
        setPosition(point.getX(), point.getY());
    }

    @Override
    public float getWidth() {
        return dimension.getWidth();
    }

    @Override
    public void setWidth(float width) {
        dimension.setWidth(width);
    }

    @Override
    public float getHeight() {
        return dimension.getHeight();
    }

    @Override
    public void setHeight(float height) {
        dimension.setHeight(height);
    }

    @Override
    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public void setDimension(float width, float height) {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void setDimension(Dimension dimension) {
        setDimension(dimension.getWidth(), dimension.getHeight());
    }

    @Override
    public boolean intersects(Shape shape) {

        if (shape instanceof Rectangle) {
            return shape.getX() <= getX() && getX() <= shape.getX() + shape.getWidth()
                && shape.getY() <= getY() && getY() <= shape.getY() + shape.getHeight();
        }

        return false;

    }

    public boolean contains(Shape shape) {
        return getX() <= shape.getX() && shape.getX() + shape.getWidth() <= getX() + getWidth()
                && getY() <= shape.getY() && shape.getY() + shape.getWidth() <= getY() + getHeight();
    }

}
