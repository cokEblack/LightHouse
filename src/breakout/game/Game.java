package breakout.game;

import breakout.game.state.GameState;

import java.awt.Graphics;

public interface Game {

    /**
     * Returns the current game state.
     *
     * @return The current game state
     */
    GameState getGameState();

    void update(int dt, GameState state);

    void render(Graphics g);

}
