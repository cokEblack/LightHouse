package breakout.game.texture;

import java.awt.image.BufferedImage;

/**
 * An {@code ImageTexture} displays a fixed image.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class ImageTexture implements Texture {

    /** The image used by this texture */
    private BufferedImage image;

    /**
     * Creates an ImageTexture with an image.
     *
     * @param image An image
     */
    public ImageTexture(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

}
