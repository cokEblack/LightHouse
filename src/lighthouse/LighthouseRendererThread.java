package lighthouse;

import breakout.game.renderer.Renderer;
import breakout.game.state.GameState;
import lighthouse.renderer.LighthouseRenderer;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * The {@code LightHouseRendererThread} handles the conversion of incoming
 * {@code GameState} objects to byte array which can be interpreted by the
 * Lighthouse controllers.
 *
 * @author Melf Kammholz
 *
 */
public class LighthouseRendererThread extends Thread {

    /**
     * Queue of {@code GameState} objects which are waiting to be processed
     * and rendered.
     *
     */
    private Queue<GameState> gameStateQueue = new ConcurrentLinkedQueue<>();

    /**
     * The renderer takes care of displaying a snapshot of the game state.
     *
     */
    private Renderer renderer = new LighthouseRenderer();

    /**
     * Enqueues {@code GameState} objects to process.
     *
     * @param state A {@code GameState} object
     */
    public synchronized void enqueueGameState(GameState state) {
        gameStateQueue.add(state);
        notifyAll();
    }

    public Renderer getRenderer() {
        return renderer;
    }

    /**
     * Waits for {@code GameState} objects and renders them to
     * display them on the Lighthouse screen.
     *
     */
    @Override
    public synchronized void run() {

        while (!isInterrupted()) {

            try {

                // Wait for game states
                while (gameStateQueue.isEmpty()) {
                    wait();
                }

            } catch (InterruptedException exception) {
                // TODO exception handling
            }

            // Take first in queue and render
            GameState state = gameStateQueue.poll();
            renderer.render(state);

        }

    }

}
