package breakout.game.api;

import breakout.game.Drawable;
import breakout.game.gameobject.GameObjectBody;
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
 * TODO provide a mechanism to give any GameObject a generic name
 *
 * TODO summarize any resource like health in a map, which allows better management of a game object's state. This should be become handy, when a game object has more than a few resources (like standard roleplay stats: strength, agility, intelligence, vitality, ...)
 *
 * TODO a health field would directly imply a damage and restoreHealth method
 *
 * TODO consider moving the update method to a diffent controller which acts a controller for this GameObject. The game object would serve solely as a model (a pure data representation).
 *
 * @author Melf Kammholz
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
     * Updates the {@code GameObject}.
     *
     * The update method should be invoked each time a global
     * update method is invoked. In other words, this object
     * will be updated each time any other {@code GameObject}
     * will be updated. This can result in any sort of
     * interaction with different {@code GameObject}s.
     *
     * @param dt The time passed since the last update
     * @param state The current state of the game
     */
    void update(int dt, GameState state);

}