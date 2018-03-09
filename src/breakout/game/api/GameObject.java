package breakout.game.api;

import breakout.game.Drawable;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GameObjectListener;
import breakout.game.gameobject.GameplayAttribute;
import breakout.game.gameobject.Health;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;

/**
 *
 * Each GameObject has to consist of the following components:
 * <ul>
 *     <li>a body which represents the physical state and</li>
 *     <li>a texture which used to render the object on screen</li>
 * </ul>
 *
 * This interface also provides a update method to allow interaction
 * with the game's state.
 *
 * Health is the only resource of each GameObject. Any further stats are
 * individual to each extending GameObject.
 *
 * Even though it is not necessary to set a name for each individual
 * {@code GameObject}, as generic names are set automatically, GUI
 * elements might profit from setting unique names to distinguish
 * game objects and adding a little bit of character, which should
 * ultimately benefit the user's experience.
 *
 *
 * TODO consider moving the update method to a different controller which acts a controller for this GameObject. The game object would serve solely as a model (a pure data representation).
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface GameObject extends Drawable {

    /**
     * Returns the physical representation of this {@code GameObject}.
     *
     * @return A body
     */
    GameObjectBody getBody();

    /**
     * Sets the body of this {@code GameObject}, which will represent
     * the {@code GameObject}'s physical state.
     *
     * @param body A body
     */
    void setBody(GameObjectBody body);

    /**
     * Returns the name of this {@code GameObject}.
     *
     * @return A name
     */
    String getName();

    /**
     * Sets the {@code GameObject}'s name.
     *
     * @param name A name
     */
    void setName(String name);

    /**
     * Returns the texture of this {@code GameObject}, which is
     * the visual representation of this {@code GameObject}.
     *
     * @return A texture
     */
    Texture getTexture();

    /**
     * Sets the texture of this {@code GameObject}.
     *
     * @param texture A texture
     */
    void setTexture(Texture texture);

    /**
     * Returns the current health.
     *
     * @return The current health
     */
    Health getHealth();

    /**
     * Sets the current health.
     *
     * @param health The health to set
     */
    void setHealth(Health health);

    /**
     * Damages this game object by a given value.
     *
     * @param damageDone The damage that has been dealt
     */
    void damage(float damageDone);

    /**
     * Restores an amount of health points.
     *
     * @param healthPoints An amount of health points
     *
     */
    void restoreHealth(float healthPoints);

    /**
     * Sets the game object's vulnerability.
     *
     * @param isVulnerable Is the game object vulnerable?
     */
    void setVulnerability(boolean isVulnerable);

    /**
     * Returns if the game object is vulnerable or not.
     *
     * @return Returns if the game object is vulnerable or not.
     */
    boolean isVulnerable();

    /**
     * Applies a buff.
     *
     * @param buff A buff
     */
    void applyBuff(Buff buff);

    /**
     * Removes a buff.
     *
     * @param buff A buff
     */
    void removeBuff(Buff buff);

    /**
     * Get data associated with this game object.
     *
     * @param key A key which grants access to the deposited data
     * @return A value associated with this game object
     */
    Object getData(GameObjectDataProperty key);

    /**
     * Sets data which is associated with this game object.
     *
     * This method uses the {@code GameObjectDataProperty} interface to check if
     * a value is an instance of the class, that is provided by the key.
     *
     * @param key A key which should grant access to the deposited data
     * @param value A value which should be associated with this game object
     * @throws IllegalArgumentException If the key is null
     *
     */
    void setData(GameObjectDataProperty key, Object value);

    /**
     * Checks if a game object is destroyed.
     *
     * Any game object that is destroyed and be considered dead.
     *
     * This method is mainly used as workaround for the destruction of game
     * objects inside collision listeners. As there is not immediate access
     * to the iterator, removing game objects from the game state results in
     * a {@code ConcurrentModificationException} which is hereby prevented.
     *
     * @return Returns {@code true} if the game object is destroyed, else
     *     {@code false}
     *
     */
    boolean isDestroyed();

    /**
     * Sets the destruction flag.
     *
     * Any game object, which is destroyed, should be safely removed from the
     * game state.
     *
     */
    void destroy();

    /**
     * Adds a {@code GameObjectListener} to this game object.
     *
     * @param listener A {@code GameObjectListener}
     */
    void addGameObjectListener(GameObjectListener listener);

    /**
     * Removes a {@code GameObjectListener} to this game object.
     *
     * @param listener A {@code GameObjectListener}
     */
    void removeGameObjectListener(GameObjectListener listener);

    /**
     * Equips this game object with a weapon.
     *
     * @param weapon A weapon
     */
    void setWeapon(Weapon weapon);

    /**
     * Returns the weapon, this game object is equipped with.
     *
     * @return The weapon, this game object is equipped with
     */
    Weapon getWeapon();

    /**
     * Returns the attribute that has been queried.
     *
     * @param name Name of the attribute
     * @return An attribute
     *
     */
    GameplayAttribute getAttribute(Object name);

    /**
     * Sets an attribute.
     *
     * @param name Name of the attribute
     * @param attribute The attribute object
     */
    void setAttribute(Object name, GameplayAttribute attribute);

    /**
     * Updates the {@code GameObject}.
     *
     * The update method should be invoked each time a global update method is
     * invoked. In other words, this object will be updated each time any other
     * {@code GameObject} will be updated. This can result in any sort of
     * interaction with different {@code GameObject}s.
     *
     * @param dt The time passed since the last update
     * @param state The current state of the game
     */
    void update(int dt, GameState state);

}