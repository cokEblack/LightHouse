package breakout.game.texture;

import breakout.game.animation.Animation;

import java.awt.image.BufferedImage;

/**
 * An {@code AnimatedTexture} is a texture that uses current frame of an
 * animation.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class AnimatedTexture implements Texture {

    /** The animation of this texture */
    private Animation animation;

    /**
     * Creates an {@code AnimatedTexture} with an animation.
     *
     * @param animation The animation displayed by this texture
     */
    public AnimatedTexture(Animation animation) {
        this.animation = animation;
    }

    @Override
    public BufferedImage getImage() {

        if (!animation.isPlaying()) {
            animation.play();
        }

        return animation.getCurrentFrame().getImage();

    }

}
