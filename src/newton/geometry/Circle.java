package newton.geometry;

import static java.lang.Math.sqrt;
import static newton.util.Math.clamp;

public class Circle implements Shape {

    private Vector position;
    private float radius;

    public Circle() {
        this(0, 0, 0);
    }

    public Circle(final float x, final float y, final float radius) {
        this(new Point(x, y), radius);
    }

    public Circle(final Point position, final float radius) {
        setPosition(position);
        setRadius(radius);
    }

    public Circle(final Circle circle) {
        this(new Point(circle.getPosition()), circle.getRadius());
    }

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

    public float getCenterX() {
        return position.getX() + getRadius();
    }

    public void setCenterX(final float cx) {
        setX(cx - getRadius());
    }

    public float getCenterY() {
        return position.getY() + getRadius();
    }

    public void setCenterY(final float cy) {
        setY(cy - getRadius());
    }

    public Vector getCenter() {
        return new Vector(getCenterX(), getCenterY());
    }

    public void setCenter(Vector center) {
        setCenterX(center.getX());
        setCenterY(center.getY());
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }



    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 2 * getRadius(), 2 * getRadius());
    }

    @Override
    public boolean contains(float x, float y) {

        float dx = x - getCenterX();
        float dy = y - getCenterY();

        return dx * dx + dy * dy <= getRadius() * getRadius();

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

        float dx = getCenterX() - circle.getCenterX();
        float dy = getCenterY() - circle.getCenterY();

        float distance = (float) sqrt(dx * dx + dy * dy);

        return getRadius() > distance + circle.getRadius();

    }

    @Override
    public boolean intersects(float cx, float cy, float radius) {

        float dx = getCenterX() - cx;
        float dy = getCenterY() - cy;

        return dx * dx + dy * dy <= (getRadius() + radius) * (getRadius() + radius);

    }

    @Override
    public boolean intersects(float x, float y, float width, float height) {

        float dx = getCenterX() - clamp(x, x + width, getCenterX());
        float dy = getCenterY() - clamp(y, y + height, getCenterY());

        return dx * dx + dy * dy < getRadius() * getRadius();

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
