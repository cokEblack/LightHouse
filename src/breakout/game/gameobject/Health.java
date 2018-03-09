package breakout.game.gameobject;

/**
 * Health is the primary resource of each game object and gives information about
 * whether the game object is dead or not.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Health extends RegenerativeGameObjectResource {

    /**
     * Creates a {@code Health} object with a current value, a maximum value
     * and a regeneration rate.
     *
     * @param currentValue The current number of health points
     * @param maximumValue The maximum number of health points
     * @param regenerationRate The regeneration rate per millisecond
     */
    public Health(float currentValue, float maximumValue, float regenerationRate) {
        super(currentValue, maximumValue, regenerationRate);
    }

}
