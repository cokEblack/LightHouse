package breakout.game.animation;


import java.util.ArrayList;
import java.util.List;

/**
 * An animation consists of an ordered collection of frames.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Animation {

    /** The animation timer which changes the frame periodically */
    private AnimationTimer timer;

    /** Whether the animation is playing or not */
    private boolean isPlaying = false;

    /** A list of frames that assemble the animation */
    private List<AnimationFrame> frames = new ArrayList<>();

    /** The current frame index */
    private int frameIndex = 0;

    /** The duration of the animation */
    private int duration;

    /**
     * Creates an animation with a duration.
     *
     * @param duration A duration
     */
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

    /**
     * Increments the frame index.
     *
     */
    public void nextFrame() {
        frameIndex++;
    }

    /**
     * Returns whether or not the animation is currently playing.
     *
     * @return Whether or not the animation is currently playing.
     */
    public boolean isPlaying() {
        return isPlaying;
    }

    /**
     * Starts the animation.
     *
     */
    public void play() {

        if (isPlaying()) return;

        isPlaying = true;

        timer.scheduleAtFixedRate();

    }

    /**
     * Pauses the animation.
     *
     */
    public void pause() {

        if (!isPlaying()) return;

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
