package breakout.game.gameobject;

/**
 * A {@code GameplayAttribute} is used to add gameplay mechanics to the game.
 *
 * By introducing further attributes to the game, the gameplay grows in complexity
 * and forces the user to adjust his strategy.
 *
 * Each {@code GameObjectResource} implements this interface.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface GameplayAttribute {

    /**
     * Returns the current value of this attribute.
     *
     * @return The current value of this attribute
     */
    float getCurrentValue();

    /**
     * Sets the current value of this attribute.
     *
     * If the current value exceeds the set maximum value, the current value is
     * automatically clamped to the maximum value. If this behavior does not
     * suffice, throw an {@code IllegalArgumentException}.
     *
     * @param currentValue The current value of this attribute
     */
    void setCurrentValue(float currentValue);

    /**
     * Returns the maximum value of this attribute.
     *
     * The current value must not exceed this value.
     *
     * The maximum value of an attribute is by default <i>infinite</i>.
     *
     * @return Returns the maximum value of this attribute
     */
    default public float getMaximumValue() {
        return Float.POSITIVE_INFINITY;
    }

    /**
     * Sets the maximum value of this attribute.
     *
     * If the maximum value is less than the current value, the current value
     * should be adjusted accordingly.
     *
     * @param maximumValue The maximum value of this attribute
     */
    void setMaximumValue(float maximumValue);

}
