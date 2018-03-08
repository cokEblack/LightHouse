package breakout.game;

import java.util.function.Consumer;

public interface GameListener {

    default void onCreate(GameLoopEvent event) {}
    default void onPostCreate(GameLoopEvent event) {}
    default void onPreUpdate(GameLoopEvent event) {}
    default void onUpdate(GameLoopEvent event) {}
    default void onPostUpdate(GameLoopEvent event) {}
    default void onPreRender(GameLoopEvent event) {}
    default void onRender(GameLoopEvent event) {}
    default void onPostRender(GameLoopEvent event) {}
    default void onClose(GameLoopEvent event) {}


    default Consumer<GameLoopEvent> getGameListenerMethod(GameLoopStage stage) {

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

    }

}
