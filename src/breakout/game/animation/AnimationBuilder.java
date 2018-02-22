package breakout.game.animation;

import breakout.game.texture.Sprite;
import breakout.physics.geometry.Rectangle;
import breakout.util.Builder;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;


public class AnimationBuilder implements Builder<Animation> {

    private List<AnimationFrame> frames;
    private int duration;

    {
        frames = new ArrayList<>();
    }

    public AnimationBuilder(int duration) {
        this.duration = duration;
    }

    public AnimationBuilder addFrame(AnimationFrame frame) {
        frames.add(frame);
        return this;
    }

    public AnimationBuilder useSprite(Sprite sprite) {

        sprite.getMapStrategy().getMappings().values().forEach(rect -> {
            BufferedImage image = sprite.getImage((Rectangle) rect);
            addFrame(new AnimationFrame(image));
        });

        return this;

    }

    @Override
    public Animation build() {
        Animation animation = new Animation(duration);
        animation.getFrames().addAll(frames);
        return animation;
    }
}
