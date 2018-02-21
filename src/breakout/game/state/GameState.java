package breakout.game.state;

import breakout.game.Game;
import breakout.game.api.GameObject;
import breakout.game.io.Keyboard;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO Either singleton or passed to any object that has to operate on the game state
 */
public class GameState extends State {

    // TODO or <? extends GameObject>?
    private List<GameObject> gameObjects;
    private Keyboard keyboard;
    private Game game;

    {
        gameObjects = new ArrayList<>();
        keyboard = new Keyboard();
    }

    public GameState() {
        this(null);
    }

    public GameState(Game game) {
        this.game = game;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public void removeGameObject(GameObject gameObject) {
        gameObjects.remove(gameObject);
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
