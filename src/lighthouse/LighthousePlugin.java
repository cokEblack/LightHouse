package lighthouse;

import breakout.game.Game;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.api.Plugin;

public class LighthousePlugin implements Plugin {

    private LighthouseRendererThread lighthouseRendererThread;
    private GameListener gameListener;

    {

        gameListener = new GameListener() {

            @Override
            public void onPostCreate(GameLoopEvent event) {

                event.getGameState().getGameObjects().forEach((gameObject) -> {
                    gameObject.setData(
                        LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR,
                        GameObjectTransformer.calcAverageColor(gameObject.getTexture().getImage())
                    );
                });

                lighthouseRendererThread.start();

            }

            @Override
            public void onPostRender(GameLoopEvent event) {
                // TODO Enqueue a copy of the game state
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
