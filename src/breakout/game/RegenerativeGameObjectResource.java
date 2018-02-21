package breakout.game;

public abstract class RegenerativeGameObjectResource extends GameObjectResource {

    private float regenerationRate = 0;

    public RegenerativeGameObjectResource(float currentValue, float maxValue, float regenerationRate) {
        super(currentValue, maxValue);
        setRegenerationRate(regenerationRate);
    }

    public float getRegenerationRate() {
        return regenerationRate;
    }

    public void setRegenerationRate(float regenerationRate) {
        this.regenerationRate = regenerationRate;
    }

    public void regenerate(int dt) {
        setCurrentValue(getCurrentValue() + dt * regenerationRate);
    }

}
