package breakout.game.gameobject;

import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.api.GameObjectDataProperty;
import breakout.game.api.Weapon;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.Health;
import breakout.game.texture.Texture;

import java.awt.Graphics;
import java.util.*;

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
     * A key-value store which holds any associated data related to
     * this game object.
     *
     */
    private Map<Object, Object> data = new HashMap<>();

    /**
     * TODO doc comment
     */
    private boolean isDestroyed = false;

    private boolean isVulnerable = true;

    private Weapon weapon;

    private List<GameObjectListener> gameObjectListeners = new LinkedList<>();

    /**
     * Force any extending class to add an default constructor
     * without any formal parameters.
     *
     */
    public AbstractGameObject() {}

    public AbstractGameObject(GameObjectBuilder builder) {

        if (builder == null) {
            throw new IllegalArgumentException("A builder must be provided to construct the object.");
        }

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

        health.addPropertyChangeListener(event -> {

            if ((float) event.getNewValue() > 0) {
                return;
            }

            // If you wanted to implement some kind of resurrection, the destruction
            // of game objects might not be a good idea.
            destroy();

        });

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
    public Object getData(GameObjectDataProperty key) {
        return data.get(key);
    }

    @Override
    public void setData(GameObjectDataProperty key, Object value) {

        if (value == null) {
            throw new IllegalArgumentException("The value must no be null.");
        }

        if (!key.getType().isInstance(value)) {
            throw new IllegalArgumentException("The value must no be an instance of " + key.getType().getName() + ".");
        }

        data.put(key, value);

    }

    @Override
    public void damage(float damageDone) {

        if (!isVulnerable()) return;

        Health health = getHealth();
        health.setCurrentValue(health.getCurrentValue() - damageDone);

    }

    @Override
    public void restoreHealth(float healthPoints) {
        Health health = getHealth();
        health.setCurrentValue(health.getCurrentValue() + healthPoints);
    }

    public void setVulnerability(boolean isVulnerable) {
        this.isVulnerable = isVulnerable;
    }

    public boolean isVulnerable() {
        return isVulnerable;
    }

    @Override
    public void applyBuff(Buff buff) {

        GameObject self = this;

        Timer buffTimer = new Timer();
        buffTimer.schedule(new TimerTask() {

            {
                buff.apply(self);
            }

            @Override
            public void run() {
                self.removeBuff(buff);
            }

        }, buff.getDuration());

    }

    @Override
    public void removeBuff(Buff buff) {
        buff.remove(this);
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void addGameObjectListener(GameObjectListener listener) {
        gameObjectListeners.add(listener);
    }

    @Override
    public void removeGameObjectListener(GameObjectListener listener) {
        gameObjectListeners.remove(listener);
    }

    @Override
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public Weapon getWeapon() {
        return weapon;
    }

    @Override
    public void draw(Graphics g) {

        if (isDestroyed()) return;

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
