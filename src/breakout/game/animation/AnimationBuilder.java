package breakout.game.animation;

import breakout.game.texture.Sprite;
import breakout.util.Builder;
import newton.geometry.Rectangle;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * An {@code AnimationBuilder} provides a fluent interface to construct an
 * animation.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class AnimationBuilder implements Builder<Animation> {

    /** A list of animation frames */
    private List<AnimationFrame> frames = new LinkedList<>();

    /** The duration of the animation */
    private int duration;

    /**
     * Creates a animation builder.
     *
     * @param duration The duration of the animation
     * @throws IllegalArgumentException If the duration of the animation is less
     *     or equal to 0.
     */
    public AnimationBuilder(int duration) {

        if (duration <= 0) {
            throw new IllegalArgumentException("The duration of the animation must be greater than 0ms.");
        }

        this.duration = duration;
    }

    /**
     * Adds an frame to the animation.
     *
     * @param frame A frame
     * @return The animation builder
     */
    public AnimationBuilder addFrame(AnimationFrame frame) {
        frames.add(frame);
        return this;
    }

    /**
     * Adds all frames that are the sprite.
     *
     * @param sprite A sprite
     * @return The animation builder
     */
    public AnimationBuilder useSprite(Sprite sprite) {

        // Use the map strategy to extract each sub image from the sprite
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
