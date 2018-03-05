package breakout.game.texture;

import breakout.game.animation.Animation;

import java.awt.image.BufferedImage;

public class AnimatedTexture implements Texture {

    private Animation animation;

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
