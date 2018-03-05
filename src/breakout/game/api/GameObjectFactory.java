package breakout.game.api;

/**
 * A {@code GameObjectFactory} is mainly used by levels to provided an
 * interface for the game to load the defined game objects like balls,
 * bricks and paddles.
 *
 * @author Melf Kammholz
 *
 * @param <T> The type of the {@code GameObject}
 */
@FunctionalInterface
public interface GameObjectFactory<T extends GameObject> {

    /**
     * This method sets the precondition that any {@code GameObject}
     * created by this factory is fully configured and ready to be
     * added to the game. The game itself will only make minor
     * adjustments like positioning.
     *
     * @return A {@code GameObject}
     */
    T create();

}
