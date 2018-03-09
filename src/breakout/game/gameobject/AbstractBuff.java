package breakout.game.gameobject;

import breakout.game.api.Buff;

/**
 * An {@code AbstractBuff} already provides the utilities to store the duration
 * of a buff.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public abstract class AbstractBuff implements Buff {

    /** The duration of this buff */
    private final int duration;

    /**
     * Creates a buff.
     *
     * @param duration The duration of this buff
     */
    public AbstractBuff(int duration) {
        this.duration = duration;
    }

    @Override
    public long getDuration() {
        return duration;
    }

}
