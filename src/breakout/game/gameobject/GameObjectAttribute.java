package breakout.game.gameobject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static newton.util.Math.clamp;

public class GameObjectAttribute implements GameplayAttribute {

    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    private float currentValue = 0;

    /**
     * This value is initialized to -1 to detect the illegal state, that
     * could occur if the current value is set before the max value is.
     *
     */
    private float maximumValue;

    public GameObjectAttribute() {
        this(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public GameObjectAttribute(float currentValue, float maximumValue) {
        setMaximumValue(maximumValue);
        setCurrentValue(currentValue);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        float oldValue = this.currentValue;
        this.currentValue = clamp(0, getMaximumValue(), currentValue);
        changes.firePropertyChange("value", oldValue, this.currentValue);
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
