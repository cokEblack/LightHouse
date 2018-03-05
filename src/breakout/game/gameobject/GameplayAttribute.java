package breakout.game.gameobject;

public interface GameplayAttribute {

    public float getCurrentValue();

    public void setCurrentValue(float currentValue);

    default public float getMaximumValue() {
        return Float.POSITIVE_INFINITY;
    }

    public void setMaximumValue(float maximumValue);

}
