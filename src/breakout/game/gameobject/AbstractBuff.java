package breakout.game.gameobject;

import breakout.game.api.Buff;

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
