package breakout.game;

/**
 * A list of all stages of the game loop.
 *
 * @author Melf Kammholz
 */
public enum GameLoopStage {

    /**
     * This stage of the game creates every game objects that are not dynamically
     * added to the game.
     *
     */
    CREATE,

    /**
     * This stage of the game loop is reached, when all {@code GameObject} are
     * created which are not created dynamically.
     *
     */
    POST_CREATE,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    PRE_UPDATE,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    UPDATE,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    POST_UPDATE,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    PRE_RENDER,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    RENDER,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * entered. This stage is reached periodically.
     *
     */
    POST_RENDER,

    /**
     * This stage of the game loop is reached, when the real game loop has been
     * terminated.
     *
     */
    CLOSE;

}
