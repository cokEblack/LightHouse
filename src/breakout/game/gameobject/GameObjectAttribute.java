package breakout.game.gameobject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import static newton.util.Math.clamp;

/**
 * A {@code GameObjectAttribute} is an attribute can be used to befenit or
 * hurt the game object that owns this attribute.
 *
 * TODO adjust the interface to support PropertyChangeListeners
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameObjectAttribute implements GameplayAttribute {

    /** Adds support to listen to property changes */
    private PropertyChangeSupport changes = new PropertyChangeSupport(this);

    /** The current value of this attribute */
    private float currentValue = 0;

    /**
     * The maximum value of this attribute.
     *
     * This value should never deceed the current value.
     *
     */
    private float maximumValue;

    /**
     * Creates a {@code GameObjectAttribute} with the current and maximum value
     * set to {@code Float.MAX_VALUE}.
     *
     * TODO i guess setting the default value for the current value to 0 is more convenient than Float.MAX_VALUE
     *
     */
    public GameObjectAttribute() {
        this(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    /**
     * Creates a {@code GameObjectAttribute} with the current and maximum value.
     *
     * @param currentValue The current value of this attribute
     * @param maximumValue The maximum value of this attribute
     *
     */
    public GameObjectAttribute(float currentValue, float maximumValue) {
        setMaximumValue(maximumValue);
        setCurrentValue(currentValue);
    }

    /**
     * Adds a {@code PropertyChangeListener} to this attribute.
     *
     * @param listener A {@code PropertyChangeListener}
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    /**
     * Removes a {@code PropertyChangeListener} from this attribute.
     *
     * @param listener A {@code PropertyChangeListener}
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changes.removePropertyChangeListener(listener);
    }

    @Override
    public float getCurrentValue() {
        return currentValue;
    }

    @Override
    public void setCurrentValue(float currentValue) {
        float oldValue = this.currentValue;
        this.currentValue = clamp(0, getMaximumValue(), currentValue);
        changes.firePropertyChange("value", oldValue, this.currentValue);
    }

    @Override
    public float getMaximumValue() {
        return maximumValue;
    }

    @Override
    public void setMaximumValue(float maximumValue) {

        if (maximumValue < 0) {
            throw new IllegalArgumentException("The value must not be a negative value.");
        }

        if (getCurrentValue() > maximumValue) {
            setCurrentValue(maximumValue);
        }

        this.maximumValue = maximumValue;

    }

}
