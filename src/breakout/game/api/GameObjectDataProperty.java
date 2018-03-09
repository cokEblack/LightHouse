package breakout.game.api;

/**
 * The game object's dynamic data store uses {@code GameObjectDataProperty}s to
 * define and change data fields.
 *
 * This interface is normally implemented by enumerations to provide an overview
 * of the properties that a plugin adds to a {@code GameObject}. Each property
 * can be accessed by an unique value defined by the enumeration.
 *
 * @author Melf Kammholz
 * @author Sebastian Feuerstein
 *
 */
public interface GameObjectDataProperty {

    /**
     * Returns the type of the data property.
     *
     * Classes can use this method to check if an object is an instance of this
     * type.
     *
     * @return The type of the data property
     *
     */
    Class getType();

}
