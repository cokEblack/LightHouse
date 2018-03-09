package breakout.game.api;

/**
 * A buff temporarily applies changes to a target. These changes
 * are restored after a certain duration.
 *
 * TODO prevent the buff from stacking if the duration of the buff is longer than the cooldown duration
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Buff {

    /**
     * Returns the duration of this buff in milliseconds.
     *
     * @return The duration of this buff in milliseconds
     */
    long getDuration();

    /**
     * Applies temporary changes to a target
     *
     * @param target A target
     * @throws IllegalArgumentException If the target is {@code null}
     */
    void apply(GameObject target);

    /**
     * Removes temporary changes to a target
     *
     * @param target A target
     * @throws IllegalArgumentException If the target is {@code null}
     */
    void remove(GameObject target);

}
