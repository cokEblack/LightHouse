package unitframeplugin;

import breakout.game.api.GameObjectDataProperty;
import breakout.game.gameobject.GameObjectResource;

import java.awt.image.BufferedImage;

/**
 * This enumeration contains any property that has been dynamically
 * added to the game object's data field.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public enum UnitFrameGameObjectProperty implements GameObjectDataProperty {

    /**
     * The {@code UNIT_FRAME_AVATAR_IMAGE} property stores the avatar image
     * that is used by the unit frame.
     *
     */
    UNIT_FRAME_AVATAR_IMAGE(BufferedImage.class),

    /**
     * The {@code UNIT_FRAME_SECONDARY_RESOURCE} property stores a reference to
     * the secondary resource that should be displayed by the unit frame.
     *
     */
    UNIT_FRAME_SECONDARY_RESOURCE(GameObjectResource.class);

    /** The type of the property */
    private Class type;

    UnitFrameGameObjectProperty(Class type) {
        this.type = type;
    }

    @Override
    public Class getType() {
        return type;
    }
}