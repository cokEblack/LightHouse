package breakout.game.api;

import breakout.game.Game;

/**
 * A plugin.
 *
 * @author Melf Kammholz
 */
public interface Plugin {

    /**
     * Registers a plugin.
     *
     * @param game A game
     */
    void register(Game game);

    /**
     * Unregisters a plugin.
     *
     * @param game A game
     */
    void unregister(Game game);

}
