package breakout.game.gameobject;

import breakout.game.api.GameObject;

import java.util.EventObject;

/**
 *
 * @author Melf Kammholz
 *
 */
public class GameObjectEvent extends EventObject {

    /**
     *
     * @throws IllegalArgumentException If the {@code source} or {@code target} is {@code null}.
     */
    public GameObjectEvent(GameObject source) {
        super(source);
    }

    /**
     */
    @Override
    public GameObject getSource() {
        return (GameObject) super.getSource();
    }

}