package breakout.game;

import java.util.function.Consumer;

/**
 * The {@code GameListener} provides an interface to react to different stages
 * of the game loop.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface GameListener {

    /**
     * This method can be used to create additional game objects and make changes
     * to the entire game like adding plugins.
     *
     * @param event A game loop event
     */
    default void onCreate(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game after the create stage.
     * This method becomes handy if the manipulation of game objects is wanted.
     *
     * @param event A game loop event
     */
    default void onPostCreate(GameLoopEvent event) {}

    /**
     * This method can be used to manipulation the game state before the actual
     * update.
     *
     * @param event A game loop event
     */
    default void onPreUpdate(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state as part of the
     * actual update.
     *
     * @param event A game loop event
     */
    default void onUpdate(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state after the actual
     * update.
     *
     * @param event A game loop event
     */
    default void onPostUpdate(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state before the actual
     * render.
     *
     * @param event A game loop event
     */
    default void onPreRender(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state at the actual
     * render.
     *
     * @param event A game loop event
     */
    default void onRender(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state after the actual
     * render.
     *
     * @param event A game loop event
     */
    default void onPostRender(GameLoopEvent event) {}

    /**
     * This method can be used to manipulate the game state after the game loop
     * has been terminated.
     *
     * @param event A game loop event
     */
    default void onClose(GameLoopEvent event) {}

    /**
     * Returns the game listener method that is related to the stage.
     *
     * @param stage A game loop stage
     * @return The game listener
     */
    default Consumer<GameLoopEvent> getGameListenerMethod(GameLoopStage stage) {

        if (stage == GameLoopStage.CREATE) {
            return this::onCreate;
        } else if (stage == GameLoopStage.POST_CREATE) {
            return this::onPostCreate;
        } else if (stage == GameLoopStage.PRE_UPDATE) {
            return this::onPreUpdate;
        } else if (stage == GameLoopStage.UPDATE) {
            return this::onUpdate;
        } else if (stage == GameLoopStage.POST_UPDATE) {
            return this::onPostUpdate;
        } else if (stage == GameLoopStage.PRE_RENDER) {
            return this::onPreRender;
        } else if (stage == GameLoopStage.RENDER) {
            return this::onRender;
        } else if (stage == GameLoopStage.POST_RENDER) {
            return this::onPostRender;
        } else if (stage == GameLoopStage.CLOSE) {
            return this::onClose;
        } else{
            throw new IllegalArgumentException("Stage not supported.");
        }

        /*
        // Java is broken
        switch (stage) {

            // Create
            case CREATE:
                return this::onCreate;
            case POST_CREATE:
                return this::onPostCreate;

            // Update
            case PRE_UPDATE:
                return this::onPreUpdate;
            case UPDATE:
                return this::onUpdate;
            case POST_UPDATE:
                return this::onPostUpdate;

            // Render
            case PRE_RENDER:
                return this::onPreRender;
            case RENDER:
                return this::onRender;
            case POST_RENDER:
                return this::onPostRender;

            // Close
            case CLOSE:
                return this::onClose;

            default:
                throw new IllegalArgumentException("Stage not supported.");

        }
        */

    }

}
