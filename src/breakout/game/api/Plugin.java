package breakout.game.api;

import breakout.game.Game;

/**
 * A plugin is a compact interface which can add functionality to the game.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
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
     * This method should do clean up the game if this plugin is removed from
     * the game dynamically.
     *
     * @param game A game
     */
    void unregister(Game game);

}
