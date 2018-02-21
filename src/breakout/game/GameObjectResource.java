package breakout.game;

public class GameObjectResource {

    private float currentValue = 0;

    /**
     * This value is initialized to -1 to detect the illegal state, that
     * could occur if the current value is set before the max value is.
     *
     * TODO consider to set the max value Float.MAX_VALUE
     */
    private float maxValue = -1;

    public GameObjectResource(float currentValue, float maxValue) {
        setMaxValue(maxValue);
        setCurrentValue(currentValue);
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {

        if (maxValue < 0) {
            throw new IllegalStateException("The max value must be set first to make sure current value does not exceed max value.");
        }

        if (currentValue < 0) {
            throw new IllegalArgumentException("The value must not be a negative value.");
        }

        this.currentValue = currentValue;
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {

        if (maxValue < 0) {
            throw new IllegalArgumentException("The value must not be a negative value.");
        }

        this.maxValue = maxValue;

    }

}
