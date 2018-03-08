package unitframeplugin;

import breakout.game.api.GameObjectDataProperty;

import java.awt.image.BufferedImage;

public enum UnitFrameGameObjectProperty implements GameObjectDataProperty {

    UNIT_FRAME_AVATAR_IMAGE(BufferedImage.class);

    private Class type;

    private UnitFrameGameObjectProperty(Class type) {
        this.type = type;
    }

    @Override
    public Class getType() {
        return type;
    }
}