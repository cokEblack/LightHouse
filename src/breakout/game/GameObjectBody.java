package breakout.game;

import breakout.game.api.GameObject;
import breakout.physics.Body;
import breakout.physics.collision.CollisionEvent;
import breakout.physics.geometry.Shape;

/**
 * The {@code GameObjectBody} mainly exists to provide a reference to
 * the {@code GameObject} which is represented by the {@code Body}
 * which will collide with other bodies.
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

    protected void fireCollisionEvent(GameObjectBody target) {
        getCollisionListeners().forEach((listener) -> {
            listener.collided(new CollisionEvent<GameObjectBody>(this, target));
        });
    }

}
