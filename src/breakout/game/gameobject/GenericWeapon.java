package breakout.game.gameobject;

import breakout.game.api.Weapon;

public class GenericWeapon implements Weapon {

    private float damage;

    public GenericWeapon(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

}
