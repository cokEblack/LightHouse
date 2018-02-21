package breakout.game.api;

public abstract class AbstractBuff implements Buff {

    private final int duration;

    public AbstractBuff(int duration) {
        this.duration = duration;
    }

    @Override
    public long getDuration() {
        return duration;
    }

}
