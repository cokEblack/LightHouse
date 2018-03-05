package breakout.game.gameobject;

import breakout.game.api.GameObject;
import breakout.game.texture.ImageTexture;
import breakout.game.texture.Texture;
import breakout.util.Builder;
import breakout.util.GenericNamingStrategy;
import breakout.util.NamingStrategy;
import newton.geometry.Circle;
import newton.geometry.Point;
import newton.geometry.Rectangle;
import newton.geometry.Vector;
import newton.physics.Body;

import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * The {@code GameObjectBuilder} provides a fluent interface to construct {@code GameObject}s.
 *
 * A {@code GameObjectBuilder} can be used as a factory. Each method, that returns
 * an important object used to construct the final {@code GameObject}, must be created
 * by a {@code Supplier}. This guarantees that every object created by this builder
 * does not share a common state.
 *
 * By default this classes uses a {@code GenericNamingStrategy}.
 *
 * @author Melf Kammholz
 */
public class GameObjectBuilder<T extends GameObject> implements Builder<GameObject> {

    private Class<?> gameObjectClass;
    private NamingStrategy namingStrategy;
    private Supplier<Health> healthSupplier;
    private Supplier<Body> bodySupplier;
    private boolean isGravityIgnored = false;

    /**
     * The current velocity does not need a supplier because a vector
     * is immutable.
     *
     */
    private Vector currentVelocity = new Vector(0, 0);

    /**
     * The current velocity does not need a supplier because a vector
     * is immutable.
     *
     */
    private Vector maximumVelocity = new Vector(0, 0);

    private Supplier<Texture> textureSupplier;

    public GameObjectBuilder(Class<? extends GameObject> gameObjectClass) {
        this.gameObjectClass = gameObjectClass;
        namingStrategy = new GenericNamingStrategy(gameObjectClass.getSimpleName());
    }

    public static GameObjectBuilder create(Class<? extends GameObject> gameObjectClass) {
        return new GameObjectBuilder(gameObjectClass);
    }

    public void setNamingStrategy(NamingStrategy namingStrategy) {
        this.namingStrategy = namingStrategy;
    }

    /**
     * Sets the name for each {@code GameObject}.
     *
     * Each {@code GameObject} created by this builder is going to share
     * the same name, if this method is used to set a name.
     *
     * @param name A name
     */
    public GameObjectBuilder<T> setName(String name) {
        // TODO find an adequate name for a naming strategy that always returns the same name
        namingStrategy = () -> name;
        return this;
    }

    public String getName() {
        return namingStrategy.createName();
    }

    public GameObjectBuilder<T> useRectangularBody(float x, float y, float width, float height, float mass) {
        bodySupplier = () -> new Body(new Rectangle(x, y, width, height), mass);
        return this;
    }

    public GameObjectBuilder<T> useCircularBody(float x, float y, float radius, float mass) {
        bodySupplier = () -> new Body(new Circle(x, y, radius), mass);
        return this;
    }

    public GameObjectBuilder<T> ignoreGravity(boolean isGravityIgnored) {
        this.isGravityIgnored = isGravityIgnored;
        return this;
    }

    public boolean isGravityIgnored() {
        return isGravityIgnored;
    }

    public Body getBody() {
        return bodySupplier.get();
    }

    public GameObjectBuilder<T> setCurrentVelocity(Point velocity) {
        currentVelocity = new Vector(velocity);
        return this;
    }

    public GameObjectBuilder<T> setCurrentVelocity(float vx, float vy) {
        return setCurrentVelocity(new Vector(vx, vy));
    }

    public Vector getCurrentVelocity() {
        return currentVelocity;
    }

    public GameObjectBuilder<T> setMaximumVelocity(Point velocity) {
        maximumVelocity = new Vector(velocity);
        return this;
    }

    public GameObjectBuilder<T> setMaximumVelocity(float vx, float vy) {
        maximumVelocity = new Vector(vx, vy);
        return this;
    }

    public Vector getMaximumVelocity() {
        return maximumVelocity;
    }

    public GameObjectBuilder<T> withTextureSupplier(Supplier<Texture> textureSupplier) {
        this.textureSupplier = textureSupplier;
        return this;
    }

    public GameObjectBuilder<T> withTextureSupplier(Consumer<BufferedImage> drawImage) {

        if (bodySupplier == null) {
            throw new IllegalStateException("To use this method properly, tell the builder which Body to use.");
        }

        Body body = bodySupplier.get();

        BufferedImage image = new BufferedImage((int) body.getWidth(), (int) body.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        drawImage.accept(image);
        image.getGraphics().dispose();

        return withTextureSupplier(() -> new ImageTexture(image));

    }


    public Texture getTexture() {
        return textureSupplier.get();
    }


    public GameObjectBuilder<T> withFullHealth(float maximumHealth, float regenerationRate) {

        // This method ensures that any GameObject constructed by this builder has
        // an individual health object.
        healthSupplier = () -> new Health(maximumHealth, maximumHealth, regenerationRate);

        return this;

    }

    public GameObjectBuilder<T> withFullHealth(float maxHealth) {
        return withFullHealth(maxHealth, 0);
    }

    public Health getHealth() {
        return healthSupplier.get();
    }

    // TODO consider adding a constructor which can take the builder, should the GameObject know that the builder exists?
    @Override
    public T build() {

        T gameObject = null;

        try {
            gameObject = (T) gameObjectClass.getDeclaredConstructor(GameObjectBuilder.class).newInstance(this);
        } catch (ReflectiveOperationException exception) {
            // TODO throw the right exception or check if a legal class has been provided in the first place (move check)
            System.err.println("Any GameObject class must implement a constructor which takes a GameObjectBuilder as its only parameter.");
            exception.printStackTrace();
        }

        return gameObject;

    }

}
