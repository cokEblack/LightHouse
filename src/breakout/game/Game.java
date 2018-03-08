package breakout.game;

import breakout.game.api.Level;
import breakout.game.api.Plugin;
import breakout.game.gui.Window;
import breakout.game.state.GameState;

import java.awt.Graphics;
import java.util.logging.Logger;

public interface Game {

    /**
     * Returns the current game state.
     *
     * @return The current game state
     */
    GameState getGameState();

    void update(int dt, GameState state);

    void render(Graphics g);

    Level getLevel();
    void setLevel(Level level);
    // void loadLevel(Level level);

    Logger getLogger();

    void addGameListener(GameListener listener);
    void removeGameListener(GameListener listener);

    void addPlugin(Plugin plugin);
    void removePlugin(Plugin plugin);
    Plugin getPlugin(Class<? extends Plugin> pluginClass);

    Window getWindow();

}
