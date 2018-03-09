package breakout.game.gameobject;

import breakout.game.api.GameObject;
import newton.geometry.Shape;
import newton.physics.Body;
import newton.physics.collision.CollisionEvent;

/**
 * The {@code GameObjectBody} mainly exists to provide a reference to
 * the {@code GameObject} which is represented by the {@code Body}.
 *
 * When two bodies collide, a {@code CollisionEvent} is dispatched and
 * a {@code Body} does not hold a reference of the related
 * {@code GameObject}.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameObjectBody extends Body {

    /** The game object related to this body. */
    private GameObject gameObject;

    /**
     * Creates a {@code GameObjectBody} with a game object that should be
     * related to this body, a shape and a mass.
     *
     * @param gameObject A game object
     * @param shape A shape
     * @param mass A mass
     *
     */
    public GameObjectBody(GameObject gameObject, Shape shape, float mass) {

        super(shape, mass);

        if (gameObject == null) {
            throw new IllegalArgumentException("A GameObject must be provided.");
        }

        this.gameObject = gameObject;

    }

    public GameObjectBody(GameObject gameObject, Body body) {
        this(gameObject, body.getShape(), body.getMass());
    }

    /**
     * Returns the game object related to this body.
     *
     * @return The game object related to this body
     */
    public GameObject getGameObject() {
        return gameObject;
    }

    /**
     * Detects collisions of different bodies inside the related world.
     *
     * This method adjusts the collision detection to ignore destroyed
     * game objects.
     *
     */
    @Override
    protected void detectCollisions() {

        // Bodies can only collide if they are in the same frame of reference.
        if (getWorld() == null) {
            return;
        }

        getWorld().getBodies().forEach((target) -> {

            // This body always collides with itself and this collision is redundant.
            if (target == this) {
                return;
            }

            boolean isDestroyed = target instanceof GameObjectBody
                    && ((GameObjectBody) target).getGameObject().isDestroyed();

            if (!isDestroyed && isCollidingWith(target)) {
                fireCollisionEvent(target);
            }

        });


    }

}
