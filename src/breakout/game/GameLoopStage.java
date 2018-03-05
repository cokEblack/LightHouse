package breakout.game;

/**
 * A list of all stages of the game loop.
 *
 * @author Melf Kammholz
 */
public enum GameLoopStage {

    CREATE,

    /**
     * This stage of the game loop is reached, when all {@code GameObject} are
     * created which are not created dynamically.
     *
     */
    POST_CREATE,

    PRE_UPDATE,
    UPDATE,
    POST_UPDATE,

    PRE_RENDER,
    RENDER,
    POST_RENDER;

}
