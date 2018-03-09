package breakout.game.gameobject;

import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.api.GameObjectDataProperty;
import breakout.game.api.Weapon;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.Health;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;

import java.awt.Graphics;
import java.util.*;

/**
 * The extending class should implement a default constructor which
 * does not take any arguments.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
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
    private Map<Object, GameplayAttribute> attributes = new HashMap<>();

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

    /** Whether this game object is destroyed or not */
    private boolean isDestroyed = false;

    /** Whether this game object is vulnerable or not */
    private boolean isVulnerable = true;

    /** The game object's weapon */
    private Weapon weapon;

    /** A list of game object listeners attached to this game object */
    private List<GameObjectListener> gameObjectListeners = new LinkedList<>();

    /**
     * Force any extending class to add an default constructor
     * without any formal parameters.
     *
     */
    public AbstractGameObject() {}

    /**
     * Creates a game object using the a builder.
     *
     * @param builder A builder which holds the necessary information to create
     *     this object
     * @throws IllegalArgumentException if the builder is not provided
     */
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
        return (Health) attributes.get(Health.class);
    }

    @Override
    public void setHealth(Health health) {

        attributes.put(Health.class, health);

        health.addPropertyChangeListener(event -> {

            if ((float) event.getNewValue() > 0) {
                return;
            }

            // If you wanted to implement some kind of resurrection, the destruction
            // of game objects might not be a good idea. But for now it has to suffice.
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

        if (key == null) {
            throw new IllegalArgumentException("The key must no be null.");
        }

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

    @Override
    public void setVulnerability(boolean isVulnerable) {
        this.isVulnerable = isVulnerable;
    }

    @Override
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

        getGameObjectListeners().forEach(gameObjectListener -> {
            gameObjectListener.onDestroy(new GameObjectEvent(this));
        });

    }

    @Override
    public void addGameObjectListener(GameObjectListener listener) {
        gameObjectListeners.add(listener);
    }

    @Override
    public void removeGameObjectListener(GameObjectListener listener) {
        gameObjectListeners.remove(listener);
    }

    protected List<GameObjectListener> getGameObjectListeners() {
        return gameObjectListeners;
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
    public GameplayAttribute getAttribute(Object name) {
        return attributes.getOrDefault(name, null);
    }

    @Override
    public void setAttribute(Object name, GameplayAttribute attribute) {
        attributes.put(name, attribute);
    }

    /**
     * Returns the attributes of this game object.
     *
     * @return The attributes of this game object
     */
    protected Map<Object, GameplayAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public void update(int dt, GameState state) {

        getBody().accelerate(dt);
        getBody().move(dt);

        if (!isDestroyed()) {

            // Regenerate every regenerative game object resource
            getAttributes().values().forEach(attribute -> {
                if (!(attribute instanceof RegenerativeGameObjectResource)) return;
                ((RegenerativeGameObjectResource) attribute).regenerate(dt);
            });

        }

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
