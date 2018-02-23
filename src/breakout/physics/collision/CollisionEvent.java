package breakout.physics.collision;

import breakout.physics.Body;

import java.util.EventObject;

/**
 * A collision event is created when a {@code CollisionListener} detects a collision
 * between two {@code GameObject}s.
 *
 * TODO Add a field which holds the information about where the two GameObject have collided
 *
 * @author Melf Kammholz
 *
 * @param <T> The type of the two bodies which collide
 */
public class CollisionEvent<T extends Body> extends EventObject {

    private final T target;

    /**
     * Constructs a {@code CollisionEvent} with a source and a target.
     *
     * @param source The source of the collision is always the object
     *               which dispatches the event.
     * @param target The target of the collision is thus the object which
     *               the source object collided with.
     * @throws IllegalArgumentException If the {@code source} or {@code target} is {@code null}.
     */
    public CollisionEvent(T source, T target) {

        super(source);

        if (target == null) {
            throw new IllegalArgumentException("The target must not be equal to null.");
        }

        this.target = target;

    }

    /**
     * Gets the target of the collision.
     *
     * @return The target of the collision
     */
    public T getTarget() {
        return target;
    }

    /**
     * Gets the source of the collision.
     *
     * Since the {@code EventObject#getSource()} method returns an
     * {@code Object} and this class explicitly demands a source
     * of type {@code T}, the typecast to {@code T} is safe.
     *
     * @return The source of the collision.
     */
    @Override
    @SuppressWarnings("unchecked")
    public T getSource() {
        return (T) super.getSource();
    }

}
