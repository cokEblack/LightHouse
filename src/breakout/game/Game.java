package breakout.game;

import breakout.game.api.Level;
import breakout.game.api.Plugin;
import breakout.game.gui.Window;
import breakout.game.state.GameState;

import java.awt.Graphics;
import java.util.logging.Logger;

/**
 * A game interface.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Game {

    /**
     * Returns the current game state.
     *
     * @return The current game state
     */
    GameState getGameState();

    /**
     * Updates the game.
     *
     * @param dt The time interval elapsed since the last tick
     * @param state The current game state
     */
    void update(int dt, GameState state);

    /**
     * Renders the game to a {@code Graphics} context
     *
     * @param g A {@code Graphics} context
     */
    void render(Graphics g);

    /**
     * Returns the current level
     *
     * @return The current level
     */
    Level getLevel();

    /**
     * Sets the current level
     *
     * @param level A level
     */
    void setLevel(Level level);

    /**
     * Returns a logger.
     *
     * @return A logger
     */
    Logger getLogger();

    /**
     * Adds a game listener to the game
     *
     * @param listener A game listener
     */
    void addGameListener(GameListener listener);

    /**
     * Removes a game listener from the game
     *
     * @param listener A game listener
     */
    void removeGameListener(GameListener listener);

    /**
     * Adds a plugin to the game
     *
     * @param plugin A plugin
     */
    void addPlugin(Plugin plugin);

    /**
     * Removes a plugin from the game.
     *
     * @param plugin
     */
    void removePlugin(Plugin plugin);

    /**
     * Returns the plugin that has been asked for
     *
     * @param pluginClass The type of the plugin to get
     * @return A plugin
     */
    Plugin getPlugin(Class<? extends Plugin> pluginClass);

    /**
     * Returns the window which is used to display the game.
     *
     * @return The window
     */
    Window getWindow();

}
