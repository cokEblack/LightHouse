package breakout.game.gameobject;

/**
 * A {@code GameObjectResource} is a dynamic {@code GameObjectAttribute}.
 *
 * Even though {@code GameObjectAttribute}s might have the same capabilities
 * as {@code GameObjectResource}, {@code GameObjectResource} are meant to be
 * used to implement resources like health, mana (...).
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameObjectResource extends GameObjectAttribute {

    /**
     * Creates a {@code GameObjectResource} with a current and maximum value.
     *
     * @param currentValue The current value of this resource
     * @param maximumValue The maximum value of this resource
     */
    public GameObjectResource(float currentValue, float maximumValue) {
        super(currentValue, maximumValue);
    }

}
