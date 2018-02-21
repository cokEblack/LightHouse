package breakout.physics;

import breakout.game.api.GameObject;
import breakout.physics.collision.CollisionEvent;
import breakout.physics.collision.CollisionListener;
import breakout.physics.geometry.Point;
import breakout.physics.geometry.Shape;
import breakout.physics.geometry.ShapeFactory;
import breakout.physics.geometry.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the physical body of a {@code GameObject} in the game.
 *
 * @author Melf Kammholz
 */
public class Body {

    private static final float DEFAULT_MASS = 1;

    private final List<CollisionListener> collisionListeners;

    /* --- Physical qualities --- */
    // TODO create physical system this body belongs to
    // TODO create move method which check if the body collides with the world edges or other bodies
    private World world;
    private float mass;
    private Shape shape;
    private Gravity gravity;

    /*
    private Vector velocity;
    private Vector maxVelocity;
    private Vector acceleration;
    */

    {
        collisionListeners = new ArrayList<>();
    }

    public Body(Shape shape, float mass) {
        this.shape = shape;
        this.mass = mass;
    }

    public Body(ShapeFactory factory, float x, float y, float width, float height, float mass) {
        this(factory.create(x, y, width, height), mass);
    }

    /**
     * Adds a collision listener to the body.
     *
     * @param listener A collision listener to add to this body
     */
    public void addCollisionListener(CollisionListener listener) {
        collisionListeners.add(listener);
    }

    /**
     * Removes a collision listener to the body.
     *
     * @param listener A collision listener to remove from this body
     */
    public void removeCollisionListener(CollisionListener listener) {
        collisionListeners.remove(listener);
    }

    /**
     * Dispatches a {@code CollisionEvent} which is passed to any object which
     * listens for collisions.
     *
     * TODO Pass information about where the two GameObject have collided.
     *
     * @param target The {@code GameObject} which collided with this {@code GameObject}
     */
    protected void fireCollisionEvent(GameObject target) {
        collisionListeners.forEach((listener) -> {
            listener.collided(new CollisionEvent(this, target));
        });
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Gets the mass of the body.
     *
     * @return The mass of the body
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets the mass of the body.
     *
     * @param mass The mass of the body.
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Returns the position of this body.
     *
     * @return The position of this body
     */
    public float getX() {
        return shape.getX();
    }

    /**
     * Relocates the body by setting a new x-position.
     *
     * @param x A new x-position
     */
    public void setX(float x) {
        shape.setX(x);
    }

    /**
     * Returns the position of this body.
     *
     * @return The position of this body
     */
    public float getY() {
        return shape.getY();
    }

    /**
     * Relocates the body by setting a new y-position.
     *
     * @param y A new y-position
     */
    public void setY(float y) {
        shape.setY(y);
    }

    public void move(float dx, float dy) {
        shape.getPosition().add(dx, dy);
    }

    public boolean isColliding() {
        // TODO reconsider if the body or the game object should detect the collision
        return false;
    }

    /**
     * Returns the position of this body.
     *
     * @return The position of this body
     */
    public Vector getPosition() {
        return shape.getPosition();
    }

    /**
     * Locates the body a the given position.
     *
     * @param x A new x-position
     * @param y A new y-position
     */
    public void setPosition(float x, float y) {
        shape.setPosition(x, y);
    }

    /**
     * Locates the body a the given position.
     *
     * @param point A new position
     */
    public void setPosition(Point point) {
        shape.setPosition(point);
    }

    /**
     * Returns the shape of this body.
     *
     * @return The Shape of this body
     */
    public Shape getShape() {
        return shape;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public void setGravity(Gravity gravity) {
        this.gravity = gravity;
    }

}
