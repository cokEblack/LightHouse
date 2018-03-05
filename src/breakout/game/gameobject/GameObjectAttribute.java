package breakout.game.gameobject;

import static newton.util.Math.clamp;

public class GameObjectAttribute implements GameplayAttribute {

    private float currentValue = 0;

    /**
     * This value is initialized to -1 to detect the illegal state, that
     * could occur if the current value is set before the max value is.
     *
     * TODO consider to set the max value Float.MAX_VALUE
     */
    private float maximumValue = -1;

    public GameObjectAttribute(float currentValue, float maxValue) {
        setMaximumValue(maxValue);
        setCurrentValue(currentValue);
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {

        if (maximumValue < 0) {
            throw new IllegalStateException("The max value must be set first to make sure current value does not exceed max value.");
        }

        /*
        // clamp over exception?
        if (currentValue < 0) {
            throw new IllegalArgumentException("The value must not be a negative value.");
        }
        */

        this.currentValue = clamp(0, getMaximumValue(), currentValue);

    }

    public float getMaximumValue() {
        return maximumValue;
    }

    public void setMaximumValue(float maximumValue) {

        if (maximumValue < 0) {
            throw new IllegalArgumentException("The value must not be a negative value.");
        }

        this.maximumValue = maximumValue;

    }

}
