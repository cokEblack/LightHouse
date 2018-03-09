package breakout.game.api;

/**
 * A weapon is used by a game object to damage a target.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Weapon {

    /**
     * Returns the weapon's damage value.
     *
     * @return The weapon's damage value
     */
    float getDamage();

}
