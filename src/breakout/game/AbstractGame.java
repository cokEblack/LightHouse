package breakout.game;

import breakout.game.api.GameObject;
import breakout.game.api.Level;
import breakout.game.api.Plugin;
import breakout.game.gui.Window;
import breakout.game.state.GameState;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

/**
 * An abstract game.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public abstract class AbstractGame implements Game, Runnable {

    /** A reference to the window of this game */
    private final Window window;

    /** The current level */
    private Level level;

    /** The current game state */
    private final GameState gameState;

    /** A list of game listeners */
    private final List<GameListener> gameListeners = new LinkedList<>();

    /** A map of plugins */
    private final HashMap<Class<? extends Plugin>, Plugin> plugins = new HashMap<>();

    /** Whether or not this game is running */
    private boolean isRunning = false;

    {

        window = new Window(this);

        // Setup handler for logger
        getLogger().addHandler(new StreamHandler(System.out, new SimpleFormatter()));

        // Save the list of game objects from pollution
        addGameListener(new GameListener() {

            @Override
            public void onPostUpdate(GameLoopEvent event) {
                event.getGameState().getGameObjects().removeIf(GameObject::isDestroyed);
            }

        });

    }

    /**
     * Creates a game with a given state.
     *
     * @param state A state
     */
    public AbstractGame(GameState state) {
        this.gameState = state;
        window.addKeyListener(state.getKeyboard());
        window.addMouseMotionListener(state.getMouse());
        window.addMouseListener(state.getMouse());
    }

    /**
     * Creates a game with a new state.
     *
     */
    public AbstractGame() {
        this(new GameState());
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public Window getWindow() {
        return window;
    }

    @Override
    public void addGameListener(GameListener listener) {
        gameListeners.add(listener);
    }

    @Override
    public void removeGameListener(GameListener listener) {
        gameListeners.remove(listener);
    }

    /**
     * Returns a list of game listeners.
     *
     * @return A list of game listeners.
     */
    protected List<GameListener> getGameListeners() {
        return new LinkedList<>(gameListeners);
    }

    @Override
    public void addPlugin(Plugin plugin) {
        plugin.register(this);
        plugins.put(plugin.getClass(), plugin);
    }

    @Override
    public void removePlugin(Plugin plugin) {
        plugin.unregister(this);
        plugins.remove(plugin.getClass());
    }

    @Override
    public Plugin getPlugin(Class<? extends Plugin> pluginClass) {
        return plugins.get(pluginClass);
    }

    /**
     * Fires a game event of a provided game loop stage.
     *
     * @param stage A game loop stage.
     */
    protected void fireGameEvent(GameLoopStage stage) {

        List<GameListener> listeners = getGameListeners();
        // Prevent ConcurrentModificationException, which occurred when game listeners
        // have been added to game
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).getGameListenerMethod(stage).accept(new GameLoopEvent(stage, this));
        }

    }

    @Override
    public Level getLevel() {
        return level;
    }

    @Override
    public void setLevel(Level level) {
        this.level = level;
        this.addGameListener(level.getGameListener());
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

        fireGameEvent(GameLoopStage.CREATE);
        fireGameEvent(GameLoopStage.POST_CREATE);

        final int OPTIMAL_TIME = 1000 / 60;

        // Measure the elapsed time of each iteration of the game loop
        long previousTime, currentTime;
        currentTime = System.currentTimeMillis();

        while (isRunning) {

            previousTime = currentTime;
            currentTime = System.currentTimeMillis();

            // Update the game
            fireGameEvent(GameLoopStage.PRE_UPDATE);
            update((int) (currentTime - previousTime), getGameState());
            fireGameEvent(GameLoopStage.UPDATE);
            fireGameEvent(GameLoopStage.POST_UPDATE);

            // Render the game
            fireGameEvent(GameLoopStage.PRE_RENDER);
            window.repaint();
            fireGameEvent(GameLoopStage.POST_RENDER);

            // TODO wrap with if clause which checks if the sleep duration is negative
            try {
                Thread.sleep(Math.max(0, currentTime + OPTIMAL_TIME - System.currentTimeMillis()));
            } catch (InterruptedException exception) {}

        }

    }

    /**
     * Updates each game object.
     *
     * @param dt The time interval passed since the last update
     * @param state The current game state
     */
    public synchronized void update(int dt, GameState state) {

        Iterator<GameObject> iterator = state.getGameObjects().iterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            gameObject.update(dt, state);
        }

    }

    /**
     * Render each drawable object to the canvas.
     */
    public synchronized void render(Graphics g) {

        // TODO I do not probably need to assemble a new list, as I do not concat
        // multiple lists, consider this an artifact of a discarded idea
        List<Drawable> drawables = new LinkedList<>(
            getGameState().getGameObjects()
        );

        drawables.forEach(drawable -> drawable.draw(g));

    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }

}
