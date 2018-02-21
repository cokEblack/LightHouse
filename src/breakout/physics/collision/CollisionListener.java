package breakout.physics.collision;

import java.util.EventListener;

/**
 * A collision listener can receive collision events to process
 * them as specified by the implementing class.
 *
 * @author Melf Kammholz
 */
public interface CollisionListener extends EventListener {

    /**
     * The {@code collided} method is invoked, when a collision between
     * two {@code GameObject}s ({@code source} and {@code target} occurs.
     *
     * @param event A collision event
     */
    void collided(CollisionEvent event);

}
