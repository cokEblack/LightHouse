package breakout.game.animation;


import java.util.ArrayList;
import java.util.List;

/**
 * An animation consists of an ordered collection of frames.
 *
 * @author Melf Kammholz
 */
public class Animation {

    private AnimationTimer timer;
    private boolean isPlaying = false;
    private List<AnimationFrame> frames = new ArrayList<>();
    private int frameIndex = 0;
    private int duration;

    public Animation(int duration) {
        this.duration = duration;
        timer = new AnimationTimer(this);
    }

    /**
     * Returns the duration of the animation in milliseconds.
     *
     * @return The duration of the animation
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Returns an ordered collection of frames.
     *
     * @return An ordered collection of frames
     */
    public List<AnimationFrame> getFrames() {
        return frames;
    }

    public void nextFrame() {
        frameIndex++;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void play() {

        if (isPlaying()) {
            return;
        }

        isPlaying = true;

        timer.scheduleAtFixedRate();

    }

    public void pause() {

        if (!isPlaying()) {
            return;
        }

        isPlaying = false;

    }

    /**
     * Returns the current frame of the animation.
     *
     * @return The current frame of the animation
     */
    public AnimationFrame getCurrentFrame() {
        return frames.get(frameIndex % frames.size());
    }

}
