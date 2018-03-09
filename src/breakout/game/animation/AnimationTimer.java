package breakout.game.animation;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The animation timer plays the animation by changing the frame index of an
 * animation.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 */
public class AnimationTimer extends Timer {

    /**
     * The task that is executed by the timer. This task increments the
     * frame index of the animation.
     *
     */
    private class AnimationTimerTask extends TimerTask {

        @Override
        public void run() {

            // Increment the frame index if the animation is playing
            if (animation.isPlaying()) {
                animation.nextFrame();
            }

        }

    }

    /** The animation played by this animation timer */
    private Animation animation;

    /**
     * Creates an AnimationTimer with an animation
     *
     * @param animation An AnimationTimer
     */
    public AnimationTimer(Animation animation) {
        this.animation = animation;
    }

    /**
     * Schedules when the frame index is incremented the next time.
     *
     */
    public void scheduleAtFixedRate() {
        int period = animation.getDuration() / animation.getFrames().size();
        super.scheduleAtFixedRate(new AnimationTimerTask(), 0, period);
    }

}
