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
 * TODO Either singleton or passed to any object that has to operate on the game state
 */
public class GameState extends State {

    /**
     * A list of all {@code GameObject}s that are part of the game.
     *
     * These objects are stored inside a list, because all objects
     * should be drawn in the same order as they are added.
     *
     */
    private List<GameObject> gameObjects;
    private Keyboard keyboard;
    private Mouse mouse;
    private Game game;
    private World world;

    {
        gameObjects = new LinkedList<>();

    }

    public GameState() {
        this(null);
    }

    public GameState(Game game) {
        this.game = game;
        keyboard = new Keyboard();
        mouse = new Mouse();
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject gameObject) {

        if (world != null && gameObject.getBody().getWorld() == null) {
            world.addBody(gameObject.getBody());
        }

        gameObjects.add(gameObject);

    }

    public void removeGameObject(GameObject gameObject) {

        if (world != null && gameObject.getBody().getWorld() == world) {
            world.removeBody(gameObject.getBody());
        }

        gameObjects.remove(gameObject);

    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Mouse getMouse() {
        return mouse;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

}
