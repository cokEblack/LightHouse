package breakout.physics.collision;

import java.util.EventObject;

/**
 * A collision event is created when a {@code CollisionListener} detects a collision
 * between two {@code GameObject}s.
 *
 * TODO Add a field which holds the information about where the two GameObject have collided
 *
 * @author Melf Kammholz
 */
public class CollisionEvent extends EventObject {

    private final Object target;

    /**
     * Constructs a {@code CollisionEvent} with a source and a target.
     *
     * TODO Replace the type Object with GameObject when it is implemented.
     *
     * @param source The source of the collision is always the object
     *               which dispatches the event.
     * @param target The target of the collision is thus the object which
     *               the source object collided with.
     * @throws IllegalArgumentException If the {@code source} or {@code target} is {@code null}.
     */
    public CollisionEvent(Object source, Object target) {

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
    public Object getTarget() {
        return target;
    }

}
