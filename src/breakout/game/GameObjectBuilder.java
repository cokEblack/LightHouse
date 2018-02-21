package breakout.game;

import breakout.game.api.GameObject;
import breakout.util.Builder;

/**
 * The {@code GameObjectBuilder} provides a fluent interface to construct {@code GameObject}s.
 *
 * @author Melf Kammholz
 */
public abstract class GameObjectBuilder implements Builder<GameObject> {

    /**
     * Used to assign unique names {@code GameObject}.
     */
    private static int counter = 0;

    private String name;
    private Health health;

    public GameObjectBuilder(String name) {
        this.name = name;
    }

    public GameObjectBuilder() {
        this("GameObject #" + (++counter));
    }

    public GameObjectBuilder withFullHealth(float maxHealth, float regenerationRate) {
        health = new Health(maxHealth, maxHealth, regenerationRate);
        return this;
    }

    public GameObjectBuilder withFullHealth(float maxHealth) {
        return withFullHealth(maxHealth, 0);
    }


    // TODO consider adding a constructor which can take the builder, should the GameObject know that the builder exists?
    @Override
    public abstract GameObject build();

}
