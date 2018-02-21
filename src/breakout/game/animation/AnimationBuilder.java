package breakout.game.animation;

import breakout.util.Builder;

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

    @Override
    public Animation build() {
        Animation animation = new Animation(duration);
        animation.getFrames().addAll(frames);
        return animation;
    }
}
