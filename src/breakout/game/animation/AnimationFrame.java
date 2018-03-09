package breakout.game.animation;

import java.awt.image.BufferedImage;

/**
 * An animation frame represent one frame of an animation.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class AnimationFrame {

    /** The image used by this frame */
    private BufferedImage image;

    /**
     * Creates an animation frame with an image.
     *
     * @param image An image
     */
    public AnimationFrame(BufferedImage image) {
        setImage(image);
    }

    /**
     * Returns the image used by this frame.
     *
     * @return An image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Sets the image of this frame.
     *
     * @param image A image
     * @throws IllegalArgumentException If the image is {@code null}
     */
    public void setImage(BufferedImage image) {

        if (image == null) {
            throw new IllegalArgumentException("The image must not be null.");
        }

        this.image = image;
    }

}
