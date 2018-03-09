package lighthouse;

import breakout.game.api.GameObjectDataProperty;

import java.awt.*;

/**
 * This enumeration contains any property that has been dynamically
 * added to the game object's data field.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public enum LighthouseGameObjectProperty implements GameObjectDataProperty {

    /**
     * The LIGHTHOUSE_FILL_COLOR property is used to colorize the
     * the pixel that is displayed on the lighthouse.
     *
     */
    LIGHTHOUSE_FILL_COLOR(Color.class);

    private Class type;

    LighthouseGameObjectProperty(Class type) {
        this.type = type;
    }

    @Override
    public Class getType() {
        return type;
    }
}
