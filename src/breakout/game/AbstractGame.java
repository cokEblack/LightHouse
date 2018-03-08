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

// TODO Game extends Runnable?
public abstract class AbstractGame implements Game, Runnable {

    private final Window window;
    private Level level;
    private final GameState gameState;
    private final List<GameListener> gameListeners = new LinkedList<>();
    private final HashMap<Class<? extends Plugin>, Plugin> plugins = new HashMap<>();
    private boolean isRunning;

    {
        window = new Window(this);
        isRunning = false;

        // Setup handler for logger
        getLogger().addHandler(new StreamHandler(System.out, new SimpleFormatter()));

    }

    public AbstractGame(GameState state) {
        this.gameState = state;
        window.addKeyListener(state.getKeyboard());
        window.addMouseMotionListener(state.getMouse());
        window.addMouseListener(state.getMouse());
    }

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

    protected List<GameListener> getGameListeners() {
        return gameListeners;
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

    protected void fireGameEvent(GameLoopStage stage) {

        List<GameListener> listeners = getGameListeners();
        for (int i = 0, n = listeners.size(); i < n; i++) {
            listeners.get(i).getGameListenerMethod(stage).accept(new GameLoopEvent(stage, this));
        }

    }

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

        long previousTime, currentTime;
        currentTime = System.currentTimeMillis();

        while (isRunning) {

            previousTime = currentTime;
            currentTime = System.currentTimeMillis();

            fireGameEvent(GameLoopStage.PRE_UPDATE);
            update((int) (currentTime - previousTime), getGameState());
            fireGameEvent(GameLoopStage.POST_UPDATE);
            fireGameEvent(GameLoopStage.PRE_RENDER);
            window.repaint();
            fireGameEvent(GameLoopStage.POST_RENDER);

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

        List<Drawable> drawables = new ArrayList<>(
            getGameState().getGameObjects()
        );

        drawables.forEach(drawable -> drawable.draw(g));

        g.dispose();

    }

    @Override
    public Logger getLogger() {
        return Logger.getLogger(getClass().getName());
    }

}
