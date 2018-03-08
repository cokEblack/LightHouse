package newton.physics;

import newton.geometry.Point;
import newton.geometry.Rectangle;
import newton.geometry.Vector;

import java.util.LinkedList;
import java.util.List;

/**
 * A world is a frame of reference for physical bodies.
 *
 * @author Melf Kammholz
 */
public class World {

    /** The boundary of this world */
    public Rectangle bounds;

    /** A list which contains any body that is related to this world */
    public List<Body> bodies = new LinkedList<>();

    /** The gravity which affects any body in this world */
    public Vector gravity = Vector.createNullVector();

    /** The world's top border */
    private Border topBorder;

    /** The world's right border */
    private Border rightBorder;

    /** The world's bottom border */
    private Border bottomBorder;

    /** The world's left border */
    private Border leftBorder;

    /**
     * Creates a world with its dimensions.
     *
     * @param width The world's width
     * @param height The world's height
     */
    public World(float width, float height) {
        this(new Rectangle(0, 0, width, height));
    }

    /**
     * Creates a world.
     *
     * @param bounds
     */
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

    public float getWidth() {
        return getBounds().getWidth();
    }

    public float getHeight() {
        return getBounds().getHeight();
    }

    public void addBody(Body body) {
        body.setWorld(this);
        bodies.add(body);
    }

    public void removeBody(Body body) {
        body.setWorld(null);
        bodies.remove(body);
    }

    public List<Body> getBodies() {
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
