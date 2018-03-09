package lighthouse;

import breakout.game.Game;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.api.Plugin;

/**
 * The {@code LighthousePlugin} adds allows the game be rendered to the lighthouse.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class LighthousePlugin implements Plugin {

    /** The thread that renders the game state */
    private LighthouseRendererThread lighthouseRendererThread;

    /** The listener that is used to hook into the game */
    private GameListener gameListener;

    {

        gameListener = new GameListener() {

            @Override
            public void onPostCreate(GameLoopEvent event) {

                // Automatically calculate the average color of a game object
                // before the actual game loop starts
                event.getGameState().getGameObjects().forEach((gameObject) -> {
                    gameObject.setData(
                        LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR,
                        GameObjectTransformer.calcAverageColor(gameObject.getTexture().getImage())
                    );
                });

                // Start the renderer thread
                lighthouseRendererThread.start();

            }

            @Override
            public void onPostRender(GameLoopEvent event) {
                // TODO Enqueue a copy of the game state
                // Enqueue the current game state
                lighthouseRendererThread.enqueueGameState(event.getGameState());
            }

        };

    }

    @Override
    public void register(Game game) {
        lighthouseRendererThread = new LighthouseRendererThread();
        game.addGameListener(gameListener);
    }

    @Override
    public void unregister(Game game) {
        game.removeGameListener(gameListener);
        lighthouseRendererThread.interrupt();
    }

}
