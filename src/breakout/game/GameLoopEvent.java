package breakout.game;

import breakout.game.state.GameState;

import java.util.EventObject;

/**
 * A {@code GameLoopEvent} provides references to the game and game state.
 *
 * @author Melf Kammholz
 *
 */
public class GameLoopEvent extends EventObject {

    /**
     * The current stage of the game loop.
     */
    private final GameLoopStage loopStage;

    /**
     * Creates a {@code GameLoopEvent}.
     *
     * @param stage The current stage of the game loop
     * @param game A reference of the game, which is the target of this event
     */
    public GameLoopEvent(GameLoopStage stage, Game game) {
        super(game);
        loopStage = stage;
    }

    /**
     * Get the instance of this game.
     *
     * @return The instance of this game
     */
    public Game getGame() {
        return (Game) super.getSource();
    }

    /**
     * Returns the current game state.
     *
     * @return The current game state
     */
    public GameState getGameState() {
        return getGame().getGameState();
    }

    /**
     * Returns the current stage of the game loop.
     *
     * @return The current stage of the game loop
     */
    public GameLoopStage getStage() {
        return loopStage;
    }

}
