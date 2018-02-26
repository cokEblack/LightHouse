package newton.geometry;

public interface Shape {

    Rectangle getBounds();

    boolean contains(float x, float y);

    default public boolean contains(Point point) {
        return contains(point.getX(), point.getY());
    }

    boolean contains(Rectangle rectangle);
    boolean contains(Circle circle);
    boolean contains(Line line);

    default public boolean contains(Shape shape) {

        if (shape instanceof Rectangle) {
            return contains((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return contains((Circle) shape);
        }

        if (shape instanceof Line) {
            return contains((Line) shape);
        }

        throw new IllegalArgumentException("Invalid shape.");

    }

    public boolean intersects(float x, float y, float width, float height);
    public boolean intersects(float cx, float cy, float radius);
    public boolean intersects(Line line);

    default public boolean intersects(Rectangle rectangle) {
        return intersects(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
    }

    default public boolean intersects(Circle circle) {
        return intersects(circle.getCenterX(), circle.getCenterY(), circle.getRadius());
    }

    default public boolean intersects(Shape shape) {

        if (shape instanceof Rectangle) {
            return intersects((Rectangle) shape);
        }

        if (shape instanceof Circle) {
            return intersects((Circle) shape);
        }

        if (shape instanceof Line) {
            return intersects((Line) shape);
        }

        throw new IllegalArgumentException("Invalid shape.");

    }

    Vector getPosition();

    void setPosition(float x,  float y);

    default public void setPosition(Point position) {
        setPosition(position.getX(), position.getY());
    }

}
