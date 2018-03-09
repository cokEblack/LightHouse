package breakout.game.texture;

import java.awt.image.BufferedImage;

/**
 * A texture can be applied to any {@code GameObject} to visualize
 * it.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Texture {

    /**
     * Returns the image that should be used as a texture.
     *
     * @return The image that should be used as a texture
     */
    BufferedImage getImage();

}
