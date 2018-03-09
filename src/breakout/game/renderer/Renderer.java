package breakout.game.renderer;

import breakout.game.state.GameState;

/**
 * A renderer renders the game state.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface Renderer {

    /**
     * Renders the given game state.
     *
     * @param state A game state
     */
    void render(GameState state);

}
