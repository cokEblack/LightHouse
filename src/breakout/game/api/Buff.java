package breakout.game.api;

public interface Buff {

    long getDuration();
    void apply(GameObject target);
    void remove(GameObject target);

}
