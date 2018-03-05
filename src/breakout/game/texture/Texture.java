package breakout.game.texture;

import java.awt.image.BufferedImage;

/**
 * A texture can be applied to any {@code GameObject} to visualize
 * it.
 *
 * @author Melf Kammholz
 */
public interface Texture {

    /**
     * Returns the image that should be used as a texture.
     *
     * @return The image that should be used as a texture
     */
    BufferedImage getImage();

    /**
     * Returns the image that should be displayed if an IOException
     * occurred while loading an image from the filesystem or if the
     * display is not capable of displaying it.
     *
     * The image returned by this method should be constructed without
     * using any IO operations to limit the exceptions and preventing
     * any {@code GameObject} from not being displayed.
     *
     * @return An image to fallback to if necessary
     */
    // BufferedImage getFallbackImage();

}
