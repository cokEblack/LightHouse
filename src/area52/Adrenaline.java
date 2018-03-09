package area52;

import breakout.game.gameobject.RegenerativeGameObjectResource;

/**
 * Adrenaline is a non-primary game object resource which is consumed by
 * while casting abilities.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Adrenaline extends RegenerativeGameObjectResource {

    /**
     * Creates an {@code Adrenaline} object with a current value, a maximum
     * value and a regeneration rate.
     *
     * @param currentValue The current value of adrenaline points
     * @param maxValue The maximum value of adrenaline points
     * @param regenerationRate The regeneration rate of adrenaline points per
     *     milliseconds
     *
     */
    public Adrenaline(float currentValue, float maxValue, float regenerationRate) {
        super(currentValue, maxValue, regenerationRate);
    }

}
