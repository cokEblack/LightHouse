package breakout.game;

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

    void create();

    void update(int dt, GameState state);

    void render(Graphics g);

    Logger getLogger();

    void addGameListener(GameListener listener);
    void removeGameListener(GameListener listener);

}
