package newton.physics;

import newton.geometry.Point;
import newton.geometry.Rectangle;
import newton.geometry.Vector;

import java.util.HashSet;
import java.util.Set;

public class World {

    public Rectangle bounds;
    public Set<Body> bodies = new HashSet<>();
    public Vector gravity = Vector.createNullVector();

    private Border topBorder;
    private Border rightBorder;
    private Border bottomBorder;
    private Border leftBorder;

    public World(float x, float y, float width, float height) {
        this(new Rectangle(x, y, width, height));
    }

    public World(Rectangle bounds) {
        setBounds(bounds);
        setBordersFromBounds(getBounds());
    }

    private void setBordersFromBounds(Rectangle bounds) {
        setBordersFromBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
    }

    private void setBordersFromBounds(float x, float y, float width, float height) {

        topBorder = new Border(this, new Point(x, y), new Point(x + width, y));
        rightBorder = new Border(this, new Point(x + width, y), new Point(x + width, y + height));
        bottomBorder = new Border(this, new Point(x + width, y + height), new Point(x, y + height));
        leftBorder = new Border(this, new Point(x, y + height), new Point(x, y));

        addBody(topBorder);
        addBody(rightBorder);
        addBody(bottomBorder);
        addBody(leftBorder);

    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public void addBody(Body body) {
        body.setWorld(this);
        bodies.add(body);
    }

    public void removeBody(Body body) {
        body.setWorld(null);
        bodies.remove(body);
    }

    public Set<Body> getBodies() {
        return this.bodies;
    }

    public Vector getGravity() {
        return gravity;
    }

    public void setGravity(Vector gravity) {

        if (gravity == null) {
            throw new IllegalArgumentException("Gravity must not be null.");
        }

        this.gravity = gravity;

    }

    public void setGravity(float ax, float ay) {
        setGravity(new Vector(ax, ay));
    }

    public Border getTopBorder() {
        return topBorder;
    }

    public Border getRightBorder() {
        return rightBorder;
    }

    public Border getBottomBorder() {
        return bottomBorder;
    }

    public Border getLeftBorder() {
        return leftBorder;
    }
}
