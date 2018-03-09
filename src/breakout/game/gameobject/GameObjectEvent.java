package breakout.game.gameobject;

import breakout.game.api.GameObject;

import java.util.EventObject;

/**
 * A {@code GameObjectEvent} provides information about an event fired by
 * actions of the {@code GameObject}.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameObjectEvent extends EventObject {

    /**
     * Creates a {@code GameObjectEvent}.
     *
     * @throws IllegalArgumentException If the {@code source} is {@code null}.
     */
    public GameObjectEvent(GameObject source) {
        super(source);
    }

    @Override
    public GameObject getSource() {
        return (GameObject) super.getSource();
    }

}