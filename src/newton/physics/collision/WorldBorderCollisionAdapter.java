package newton.physics.collision;

import newton.physics.Body;
import newton.physics.Border;
import newton.physics.World;

/**
 * This class helps observing any collision that will occur if a body hits the
 * world's borders.
 *
 * @author Melf Kammholz
 *
 */
public abstract class WorldBorderCollisionAdapter implements CollisionListener<Body> {

    /**
     * A world with borders.
     */
    private final World world;

    /**
     * Creates a {@code WorldBorderCollisionAdapter} with a world of which
     * the borders are observed by this listener.
     *
     * @param world A world with borders
     */
    public WorldBorderCollisionAdapter(World world) {
        this.world = world;
    }

    /**
     * This method filter any collision event that represents a collision
     * with a border. Furthermore, it checks if the border that was hit
     * by an object is part of the world's borders and calls the according
     * method.
     *
     * @param event A collision event
     */
    @Override
    public final void onCollision(CollisionEvent event) {

        // Ignore any event that does not have a border as its target.
        if (!(event.getTarget() instanceof Border)) {
            return;
        }

        Border border = (Border) event.getTarget();

        // Distribute the event to the right handler.
        if (border == world.getTopBorder()) {
            onCollisionWithTopBorder(event);
        } else if (border == world.getRightBorder()) {
            onCollisionWithRightBorder(event);
        } else if (border == world.getBottomBorder()) {
            onCollisionWithBottomBorder(event);
        } else if (border == world.getLeftBorder()) {
            onCollisionWithLeftBorder(event);
        }

        // At this point the border that has been provided is not part of
        // set which assembles the world's borders. It is silently ignored.

    }

    /**
     * This method is invoked, when a body hits the top border of the
     * world.
     *
     * @param event A collision event of which the target is the top border
     *              of the world.
     */
    public abstract void onCollisionWithTopBorder(CollisionEvent event);

    /**
     * This method is invoked, when a body hits the right border of the
     * world.
     *
     * @param event A collision event of which the target is the right border
     *              of the world.
     */
    public abstract void onCollisionWithRightBorder(CollisionEvent event);

    /**
     * This method is invoked, when a body hits the bottom border of the
     * world.
     *
     * @param event A collision event of which the target is the bottom border
     *              of the world.
     */
    public abstract void onCollisionWithBottomBorder(CollisionEvent event);

    /**
     * This method is invoked, when a body hits the left border of the
     * world.
     *
     * @param event A collision event of which the target is the left border
     *              of the world.
     */
    public abstract void onCollisionWithLeftBorder(CollisionEvent event);

}
