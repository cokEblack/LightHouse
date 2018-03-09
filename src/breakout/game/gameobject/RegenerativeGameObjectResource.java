package breakout.game.gameobject;

/**
 * A {@code RegenerativeGameObjectResource} is extends the {@code GameObjectResource}
 * by adding the capability of regeneration.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public abstract class RegenerativeGameObjectResource extends GameObjectResource {

    /** The regeneration rate per millisecond */
    private float regenerationRate = 0;

    /**
     * Creates a {@code RegenerativeGameObjectResource} with a current value,
     * a maximum value and a regeneration rate.
     *
     * @param currentValue The current number of health points
     * @param maximumValue The maximum number of health points
     * @param regenerationRate The regeneration rate per millisecond
     */
    public RegenerativeGameObjectResource(float currentValue, float maximumValue, float regenerationRate) {
        super(currentValue, maximumValue);
        setRegenerationRate(regenerationRate);
    }

    /**
     * Returns the regeneration rate per millisecond.
     *
     * @return The regeneration rate per millisecond
     */
    public float getRegenerationRate() {
        return regenerationRate;
    }

    /**
     * Sets the regeneration rate per millisecond.
     *
     * @param regenerationRate The regeneration rate per millisecond
     */
    public void setRegenerationRate(float regenerationRate) {
        this.regenerationRate = regenerationRate;
    }

    /**
     * Regenerates the resource by the result of the elapsed time and the
     * regeneration rate per millisecond.
     *
     * @param dt A time interval
     */
    public void regenerate(int dt) {
        setCurrentValue(getCurrentValue() + dt * regenerationRate);
    }

}
