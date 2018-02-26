package newton.geometry;

import static java.lang.Math.sqrt;

/**
 * A line represents
 */
public class Line implements Shape {

    private Vector position;
    private Vector direction;

    public Line() {
        this(0, 0, 0, 0);
    }

    public Line(float x, float y, float dx, float dy) {
        this(new Point(x, y), new Point(dx, dy));
    }

    public Line(Point position, Point direction) {
        setPosition(position);
        setDirection(direction);
    }

    @Override
    public String toString() {
        return String.format(
                "%s {position=%s, direction=%s}",
                getClass().getName(),
                position,
                direction
        );
    }

    public static Line createFromTwoPoints(Point p1, Point p2) {
        float dx = p2.getX() - p1.getX();
        float dy = p2.getY() - p1.getY();
        return new Line(p1.getX(), p1.getY(), dx, dy);
    }



    @Override
    public Vector getPosition() {
        return position;
    }

    public void setPosition(Point position) {

        if (position == null) {
            throw new IllegalArgumentException("The position must not be null.");
        }

        this.position = new Vector(position);

    }

    public void setPosition(float x, float y) {
        setPosition(new Point(x, y));
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Point direction) {

        if (position == null) {
            throw new IllegalArgumentException("The direction must not be null.");
        }

        this.direction = new Vector(direction);

    }

    public void setDirection(float dx, float dy) {
        setDirection(new Point(dx, dy));
    }

    @Override
    public Rectangle getBounds() {

        float x = position.getX();
        float y = position.getY();

        float width = position.getX() + direction.getX();
        float height = position.getY() + direction.getY();

        return new Rectangle(
                Math.min(x, x + width),
                Math.min(y, y + height),
                Math.abs(width),
                Math.abs(height)
        );

    }

    @Override
    public boolean contains(float x, float y) {
        return (x - position.getX()) / direction.getX() == (y - position.getY()) / direction.getY();
    }

    @Override
    public boolean contains(Rectangle rectangle) {
        return false;
    }

    @Override
    public boolean contains(Circle circle) {
        return false;
    }

    @Override
    public boolean intersects(float x, float y, float width, float height) {
        return intersects(new Line(x, y, width, 0))
                || intersects(new Line(x + width, y, 0, height))
                || intersects(new Line(x + width, y + height, -1 * width, 0))
                || intersects(new Line(x, y + height, 0, -1 * height));

    }

    @Override
    public boolean intersects(float cx, float cy, float radius) {

        // https://math.stackexchange.com/questions/228841/how-do-i-calculate-the-intersections-of-a-straight-line-and-a-circle

        float m = direction.getY() / direction.getX();

        if (direction.getX() == 0) {

            float y1 = position.getY();
            float y2 = y1 + direction.getY();
            float minY = Math.min(y1, y2), maxY = Float.max(y1, y2);

            float x = position.getX();
            float y = (float) sqrt(radius * radius - (x - cx) * (x - cx)) - cy;

            return (minY <= y && y <= maxY) || (minY <= -1 * y && -1 * y <= maxY);

        }

        float c = position.getY() - m * position.getX();

        float p = cx;
        float q = cy;
        float r = radius;

        float A = m * m + 1;
        float B = 2 * (m * c - m * q - p);
        float C = q * q - r * r + p * p - 2 * c * q + c * c;

        return B * B - 4 * A * C >= 0;

    }


    public boolean intersects(Line line) {


        // https://stackoverflow.com/questions/563198/whats-the-most-efficent-way-to-calculate-where-two-line-segments-intersect

        // cross product v × w to be vx wy − vy wx

        Vector p = position;
        Vector r = direction;

        Vector q = line.getPosition();
        Vector s = line.getDirection();

        Vector qp = q.subtract(p);
        float qpr = qp.getX() * r.getY() - qp.getY() * r.getX();
        float qps = qp.getX() * s.getY() - qp.getY() * s.getX();
        float rs = r.getX() * s.getY() - r.getY() * s.getX();

        if (rs == 0 && qpr == 0) {
            return true;
        } else if (rs == 0 && qpr != 0) {
            return false;
        } else {

            float t = qps / rs;
            float u = qpr / rs;

            return 0 <= t && t <= 1 && 0 <= u && u <= 1;

        }

    }

    @Override
    public boolean contains(Line line) {

        Vector p = position;
        Vector r = direction;

        Vector q = line.getPosition();
        Vector s = line.getDirection();

        Vector qp = q.subtract(p);
        float qpr = qp.getX() * r.getY() - qp.getY() * r.getX();
        float rs = r.getX() * s.getY() - r.getY() * s.getX();

        return rs == 0 && qpr == 0;

    }
}
