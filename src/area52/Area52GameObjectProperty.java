package area52;

import breakout.game.api.GameObjectDataProperty;

/**
 * This enumeration contains any property that has been dynamically
 * added to the game object's data field.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public enum Area52GameObjectProperty implements GameObjectDataProperty {

    /** A list of properties this plugin adds to the game object */
    AREA52_IGNI_PARTICLE_HIT_COUNT(Integer.class);

    /** The type of the property */
    private Class type;

    Area52GameObjectProperty(Class type) {
        this.type = type;
    }

    @Override
    public Class getType() {
        return type;
    }

}