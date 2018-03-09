package breakout.game.gameobject;

import breakout.game.api.Weapon;

/**
 * A {@code GenericWeapon} is a simple weapon which stores how much damage
 * can be dealt by using it.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GenericWeapon implements Weapon {

    /** The damage that can be done with this weapon */
    private float damage;

    /**
     * Creates a {@code GenericWeapon}.
     *
     * @param damage The damage that can be done with weapon
     */
    public GenericWeapon(float damage) {
        this.damage = damage;
    }

    /**
     * Returns the damage that can be done with weapon
     *
     * @return The damage that can be done with weapon
     */
    public float getDamage() {
        return damage;
    }

}
