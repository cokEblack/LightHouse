package breakout.game;

import breakout.game.api.GameObject;
import breakout.game.gui.Window;
import breakout.game.state.GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

public abstract class AbstractGame implements Game, Runnable {

    {

    }

    private final Window window;
    private final GameState gameState;
    private boolean isRunning;
    private long frames = 0;
    private long startedAt = 0;

    {
        window = new Window(this);
        isRunning = false;

        // Setup handler for logger
        getLogger().addHandler(new StreamHandler(System.out, new SimpleFormatter()));

    }

    public AbstractGame(GameState state) {
        this.gameState = state;
        window.addKeyListener(state.getKeyboard());
    }

    public AbstractGame() {
        this(new GameState());
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    public Window getWindow() {
        return window;
    }

    /**
     * Stops the game loop.
     */
    public void stop() {
        isRunning = false;
    }

    /**
     * Starts the game loop.
     */
    public void run() {

        window.setVisible(true);
        isRunning = true;
        startedAt = System.currentTimeMillis();

        create();

        final int OPTIMAL_TIME = 1000 / 60;

        long previousTime, currentTime;
        currentTime = System.currentTimeMillis();

        while (isRunning) {

            previousTime = currentTime;
            currentTime = System.currentTimeMillis();

            update((int) (currentTime - previousTime), getGameState());
            window.repaint();

            try {
                Thread.sleep(Math.max(0, currentTime + OPTIMAL_TIME - System.currentTimeMillis()));
            } catch (InterruptedException exception) {
                // TODO do something proper
            }

        }


    }

    /**
     * Updates each game object.
     *
     * @param dt The time interval passed since the last update
     * @param state The current game state
     */
    public synchronized void update(int dt, GameState state) {

        // Iterator to avoid ConcurrentModificationException
        // https://stackoverflow.com/questions/18448671/how-to-avoid-concurrentmodificationexception-while-removing-elements-from-arr
        Iterator<GameObject> iterator = state.getGameObjects().iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            gameObject.update(dt, state);
        }

        // throws ConcurrentModificationException when GameObjects are
        // added or deleted from the list
        /*
        state.getGameObjects().forEach(gameObject -> {
            gameObject.update(dt, state);
        });
        */

    }

    /**
     * Render each drawable object to the canvas.
     */
    public synchronized void render(Graphics g) {

        // frames++;

        // TODO enable antialiasing?

        // TODO Constructing a list each time the render method is called is a huge performance issue, if the optimal time is exceeded inside the game loop, drop this...
        List<Drawable> drawables = new ArrayList<>(
            getGameState().getGameObjects()
        );

        drawables.forEach(drawable -> drawable.draw(g));

        // This fps counter might throw an ArithmeticException if the whole system
        // is not done initializing.
        // TODO find an eloquent solution for this problem which might not involve if-clauses or try-catch
        // g.drawString("FPS: " + frames / ((System.currentTimeMillis() - startedAt) / 1000), 16, 16);

        g.dispose();

    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }
}
