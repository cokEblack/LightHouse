package breakout.game.api;

/**
 * An ability permanently applies changes to a target.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Ability {

    /**
     * Applies permanent changes to a target.
     *
     * @param target A target
     * @throws IllegalArgumentException If the target is {@code null}
     */
    void apply(GameObject target);

}
