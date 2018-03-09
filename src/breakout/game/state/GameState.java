package breakout.game.state;

import breakout.game.Game;
import breakout.game.api.GameObject;
import breakout.game.io.Keyboard;
import breakout.game.io.Mouse;
import newton.physics.World;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * The game state contains the whole data representation of the game, it is used
 * to render the game.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameState {

    /**
     * A list of all {@code GameObject}s that are part of the game.
     *
     * These objects are stored inside a list, because all objects should be
     * drawn in the same order as they are added.
     *
     */
    private List<GameObject> gameObjects = new LinkedList<>();

    /** The keyboard used to interact with the game */
    private Keyboard keyboard;

    /** The mouse used to interact with the game */
    private Mouse mouse;

    /** A reference to the game */
    private Game game;

    /** A reference to the world */
    private World world;

    /**
     * Creates a game state with no reference to a game.
     *
     */
    public GameState() {
        this(null);
    }

    /**
     * Creates a game state with a reference to a game.
     *
     * @param game A game
     */
    public GameState(Game game) {
        this.game = game;
        keyboard = new Keyboard();
        mouse = new Mouse();
    }

    /**
     * Returns a new list which contains every game object of the state.
     *
     * @return A new list of game objects
     */
    public List<GameObject> getGameObjects() {
        return new LinkedList<>(gameObjects);
    }

    /**
     * Adds game objects to the game state.
     *
     * If the game object has not been assigned to any world yet, then the
     * game object's world is set to the world that is referenced in the
     * game state.
     *
     * @param gameObject A game object
     */
    public void addGameObject(GameObject gameObject) {

        if (world != null && gameObject.getBody().getWorld() == null) {
            world.addBody(gameObject.getBody());
        }

        gameObjects.add(gameObject);

    }

    /**
     * Removes a game object from the game state.
     *
     * @param gameObject A game object
     */
    public void removeGameObject(GameObject gameObject) {

        if (world != null && gameObject.getBody().getWorld() == world) {
            world.removeBody(gameObject.getBody());
        }

        gameObjects.remove(gameObject);

    }

    /**
     * Returns the keyboard.
     *
     * @return The keyboard
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * Returns the mouse.
     *
     * @return The mouse
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * Returns a reference to the game.
     *
     * @return A reference to the game
     */
    public Game getGame() {
        return game;
    }

    /**
     * Sets a reference to the game.
     *
     * @param game A reference to game
     */
    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Returns the world.
     *
     * @return The world
     */
    public World getWorld() {
        return world;
    }

    /**
     * Sets the world
     *
     * @param world The world
     */
    public void setWorld(World world) {
        this.world = world;
    }

}
