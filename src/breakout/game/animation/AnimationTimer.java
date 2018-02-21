package breakout.game.animation;

import java.util.Timer;
import java.util.TimerTask;

public class AnimationTimer extends Timer {

    private class AnimationTimerTask extends TimerTask {

        @Override
        public void run() {
            if (animation.isPlaying()) {
                animation.nextFrame();
            }
        }

    }

    private Animation animation;

    public AnimationTimer(Animation animation) {
        this.animation = animation;
    }

    public void scheduleAtFixedRate() {
        int period = animation.getDuration() / animation.getFrames().size();
        super.scheduleAtFixedRate(new AnimationTimerTask(), 0, period);
    }

}
