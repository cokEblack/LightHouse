package breakout.game.api;

import breakout.game.GameObjectBody;
import breakout.game.Health;
import breakout.game.texture.Texture;

import java.awt.Graphics;
import java.util.logging.Logger;
import java.util.logging.StreamHandler;

/**
 * The extending class should implement a default constructor which
 * does not take any arguments.
 *
 * @author Melf Kammholz
 *
 */
public abstract class AbstractGameObject implements GameObject {

    /** The name of this GameObject */
    private String name;

    /**
     * The body of this GameObject.
     *
     * The body holds relevant data about physical state
     * of this GameObject which used to calculate any sort
     * of impact.
     *
     */
    private GameObjectBody body;

    /**
     * The health of this GameObject and gives information
     * about the vitality of this GameObject.
     *
     */
    private Health health;

    /**
     * The texture of this GameObject.
     *
     * The texture that is used to display this GameObject
     * inside the window.
     */
    private Texture texture;

    /**
     * Force any extending class to add an default constructor
     * without any formal parameters.
     *
     */
    public AbstractGameObject() {}

    @Override
    public GameObjectBody getBody() {
        return body;
    }

    @Override
    public void setBody(GameObjectBody body) {
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void setHealth(Health health) {
        this.health = health;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                getTexture().getImage(),
                (int) getBody().getPosition().getX(),
                (int) getBody().getPosition().getY(),
                (int) getBody().getShape().getBounds().getWidth(),
                (int) getBody().getShape().getBounds().getHeight(),
                null
        );
    }

}
