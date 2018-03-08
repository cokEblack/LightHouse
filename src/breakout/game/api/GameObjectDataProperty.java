package breakout.game.api;

/**
 * The game object's dynamic data store uses {@code GameObjectDataProperty}s to
 * define and change fields.
 *
 * @author Melf Kammholz
 */
public interface GameObjectDataProperty {

    /**
     * Returns the type of the data property.
     *
     * @return The type of the data property
     */
    Class getType();

}
