package newton.geometry;

import static newton.util.Math.clamp;

public class Rectangle implements Shape {

    private Vector position;
    private Dimension dimension;

    public Rectangle() {
        this(0, 0, 0, 0);
    }

    public Rectangle(final float x, final float y, final float width, final float height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    public Rectangle(final Point position, final Dimension dimension) {
        setPosition(position);
        setDimension(dimension);
    }

    public Rectangle(final Rectangle rectangle) {
        this(
                new Vector(rectangle.getPosition()),
                new Dimension(rectangle.getDimension())
        );
    }

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

    public float getX() {
        return position.getX();
    }

    public void setX(final float x) {
        position = position.setX(x);
    }

    public float getY() {
        return position.getY();
    }

    public void setY(final float y) {
        position = position.setY(y);
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public float getWidth() {
        return dimension.getWidth();
    }

    public void setWidth(final float width) {
        dimension.setWidth(width);
    }

    public float getHeight() {
        return dimension.getHeight();
    }

    public void setHeight(final float height) {
        dimension.setHeight(height);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(this);
    }

    @Override
    public boolean contains(float x, float y) {
        return getX() <= x && x <= getX() + getWidth()
                && getY() <= y && y <= getY() + getHeight();
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
        return contains(x, y)
                || contains(x + width, y)
                || contains(x + width, y + height)
                || contains(x, y + height);
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
