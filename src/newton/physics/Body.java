package newton.physics;

import newton.geometry.Point;
import newton.geometry.Rectangle;
import newton.geometry.Shape;
import newton.geometry.Vector;
import newton.physics.collision.CollisionEvent;
import newton.physics.collision.CollisionListener;

import java.util.HashSet;
import java.util.Set;

import static newton.util.Math.clamp;
import static java.lang.Math.signum;

public class Body {

    /**
     * A set of collision listeners.
     */
    private final Set<CollisionListener> collisionListeners = new HashSet<>();

    /**
     * The world which this body belongs to.
     */
    private World world;

    /**
     * The shape of this body.
     */
    private Shape shape;

    /**
     * The mass of this body.
     */
    private float mass;

    /**
     * The current velocity of this body.
     */
    private Vector velocity = Vector.createNullVector();

    /**
     * The maximum velocity of this body.
     */
    private Vector maximumVelocity = new Vector(Float.MAX_VALUE, Float.MAX_VALUE);

    /**
     * The current acceleration of this body.
     */
    private Vector acceleration = Vector.createNullVector();

    private boolean isGravityIgnored = false;

    /**
     * Creates a body with a shape, a mass and a world.
     *
     * @param shape The shape of this body
     * @param mass The mass of this body
     * @param world The world of this body
     */
    public Body(Shape shape, float mass, World world) {
        setShape(shape);
        setMass(mass);
        setWorld(world);
    }

    @Override
    public String toString() {
        return String.format(
                "%s {shape=%s {...}, mass=%.3f}",
                getClass().getName(),
                getShape().getClass().getName(),
                getMass()
        );
    }

    /**
     * Creates a body with a shape and a mass.
     *
     * This body does not belong to any world. Therefore this
     * body can move freely.
     *
     * @param shape The shape of this body
     * @param mass The mass of this body
     */
    public Body(Shape shape, float mass) {
        this(shape, mass, null);
    }

    public Rectangle getBounds() {
        return getShape().getBounds();
    }

    public float getX() {
        return getPosition().getX();
    }

    public float getY() {
        return getPosition().getY();
    }

    public void setX(float x) {
        System.out.println(this + " -> x: " + x);
        shape.setPosition(shape.getPosition().setX(x));
    }

    public void setY(float y) {
        shape.setPosition(shape.getPosition().setY(y));
    }

    public float getWidth() {
        return getBounds().getWidth();
    }

    public float getHeight() {
        return getBounds().getHeight();
    }

    public void setPosition(Point position) {
        shape.setPosition(position);
    }

    public void setPosition(float x, float y) {
        shape.setPosition(new Vector(x, y));
    }

    public Vector getPosition() {
        return shape.getPosition();
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
    protected void fireCollisionEvent(Body target) {
        getCollisionListeners().forEach((listener) -> {
            listener.onCollision(new CollisionEvent<Body>(this, target));
        });
    }

    /**
     * Checks if this body is colliding with a different body of the world, this body
     * belongs to. If this body is colliding, a {@code CollisionEvent} will be
     * dispatched for each collision with a different body.
     *
     */
    protected void detectCollisions() {

        // Bodies can only collide if they are in the same frame of reference.
        if (world == null) {
            return;
        }

        getWorld().getBodies().forEach((target) -> {

            // This body always collides with itself and this collision is redundant.
            if (target == this) {
                return;
            }

            if (isCollidingWith(target)) {
                fireCollisionEvent(target);
            }

        });

    }

    /**
     * Returns all collision listeners.
     *
     * @return All collision listeners
     */
    protected Set<CollisionListener> getCollisionListeners() {
        return collisionListeners;
    }

    /**
     * Moves this body.
     *
     * @param dx
     * @param dy
     */
    public void move(float dx, float dy) {

        /*
        if (dx != 0 || dy != 0) {
            System.out.println(new Point(dx, dy));
        }
        */

        Vector position = shape.getPosition();

        // A body can be moved freely if it does not belong to a frame of reference.
        if (world == null) {

            position = position.add(dx, dy);

        } else {

            // A body is bound to its world
            Rectangle bounds = getWorld().getBounds();

            float bodyWidth = getShape().getBounds().getWidth();
            float bodyHeight = getShape().getBounds().getHeight();

            float x = clamp(bounds.getX(), bounds.getX() + bounds.getWidth() - bodyWidth, position.getX() + dx);
            float y = clamp(bounds.getY(), bounds.getY() + bounds.getHeight() - bodyHeight, position.getY() + dy);

            position = new Vector(x, y);

        }

        shape.setPosition(position);

        detectCollisions();

    }

    public void move(Point vector) {
        move(vector.getX(), vector.getY());
    }

    public void move(float dt) {
        move(getVelocity().scale(dt));
    }

    public Vector getAcceleration() {

        if (world == null || isGravityIgnored()) {
            return acceleration;
        }

        return acceleration.add(getWorld().getGravity());

    }

    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Sets the velocity of this body.
     *
     * The velocity is automatically limited to the maximum
     * velocity, which by default the maximum value of a float
     * number. If a velocity is set that exceeds the maximum
     * velocity in either positive and negative x- or y-direction,
     * the value that is going to be set, is the maximum velocity
     * of the particular direction.
     *
     * @param velocity The velocity to set
     * @throws IllegalArgumentException If a velocity has not been
     *     provided for this body
     */
    public void setVelocity(Vector velocity) {

        if (velocity == null) {
            throw new IllegalArgumentException("A velocity must be provided.");
        }

        // The maximum velocity in x-direction and in y-direction
        // is a absolute value, which means, the maximum velocity
        // gives both, a minimum and maximum velocity.

        if (Math.abs(velocity.getX()) > maximumVelocity.getX()) {
            this.velocity = getVelocity().setX(signum(velocity.getX()) * maximumVelocity.getX());
        } else {
            this.velocity = getVelocity().setX(velocity.getX());
        }

        if (Math.abs(velocity.getY()) > maximumVelocity.getY()) {
            this.velocity = getVelocity().setY(signum(velocity.getY()) * maximumVelocity.getY());
        } else {
            this.velocity = getVelocity().setY(velocity.getY());
        }

    }

    public void setVelocityX(float vx) {
        setVelocity(new Vector(vx, getVelocity().getY()));
    }

    public void setVelocityY(float vy) {
        setVelocity(new Vector(getVelocity().getX(), vy));
    }

    public void addVelocity(float vx, float vy) {
        setVelocity(velocity.add(vx, vy));
    }

    public void addVelocity(Vector velocity) {
        setVelocity(this.velocity.add(velocity));
    }

    public void accelerate(float dt) {
        addVelocity(getAcceleration().scale(dt * dt));

    }

    public void setMaximumVelocity(Vector maximumVelocity) {
        this.maximumVelocity = maximumVelocity;
    }

    public Vector getMaximumVelocity() {
        return maximumVelocity;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {

        if (shape == null) {
            throw new IllegalArgumentException("\"shape\" must not be null.");
        }

        this.shape = shape;
    }

    public boolean isCollidingWith(Body target) {
        return getShape().intersects(target.getShape());
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {

        if (mass < 0) {
            throw new IllegalArgumentException("A mass must not be a negative value.");
        }

        this.mass = mass;

    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public boolean isGravityIgnored() {
        return isGravityIgnored;
    }

    public void ignoreGravity(boolean isGravityIgnored) {
        this.isGravityIgnored = isGravityIgnored;
    }

}
