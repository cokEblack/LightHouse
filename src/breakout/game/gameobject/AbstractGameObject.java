package breakout.game.gameobject;

import breakout.game.api.GameObject;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.Health;
import breakout.game.texture.Texture;

import java.awt.Graphics;

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
     * The attributes of this GameObject.
     *
     */
    private GameplayAttributeMap attributes = new GameplayAttributeMap();

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

    public AbstractGameObject(GameObjectBuilder builder) {

        setName(builder.getName());
        setHealth(builder.getHealth());

        GameObjectBody body = new GameObjectBody(this, builder.getBody());
        body.setMaximumVelocity(builder.getMaximumVelocity());
        body.setVelocity(builder.getCurrentVelocity());
        body.ignoreGravity(builder.isGravityIgnored());
        setBody(body);

        setTexture(builder.getTexture());

    }

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
        return (Health) attributes.getAttribute(GameplayAttributeMap.DefaultAttribute.HEALTH);
    }

    @Override
    public void setHealth(Health health) {
        attributes.setAttribute(GameplayAttributeMap.DefaultAttribute.HEALTH, health);
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
