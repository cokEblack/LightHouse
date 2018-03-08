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
 */
public class GameObjectBody extends Body {

    private GameObject gameObject;

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

    public GameObject getGameObject() {
        return gameObject;
    }

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

    protected void fireCollisionEvent(GameObjectBody target) {
        getCollisionListeners().forEach((listener) -> {
            listener.onCollision(new CollisionEvent<GameObjectBody>(this, target));
        });
    }

}
