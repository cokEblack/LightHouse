package breakout.game;

import breakout.game.api.GameObject;
import breakout.game.texture.Texture;
import breakout.util.Builder;
import newton.geometry.Point;
import newton.geometry.Rectangle;
import newton.geometry.Vector;
import newton.physics.Body;

import java.awt.image.BufferedImage;
import java.util.function.Supplier;

/**
 * The {@code GameObjectBuilder} provides a fluent interface to construct {@code GameObject}s.
 *
 * @author Melf Kammholz
 */
public class GameObjectBuilder<T extends GameObject> implements Builder<GameObject> {

    /**
     * Used to assign unique names {@code GameObject}.
     */
    private static int counter = 0;

    private Class<?> gameObjectClass;
    private String name;
    private Supplier<Health> healthSupplier;
    private Supplier<Body> bodySupplier;

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
    private Supplier<BufferedImage> textureSupplier;

    public GameObjectBuilder(Class<? extends GameObject> gameObjectClass) {
        this.gameObjectClass = gameObjectClass;
    }

    public static GameObjectBuilder create(Class<? extends GameObject> gameObjectClass) {
        return new GameObjectBuilder(gameObjectClass);
    }


    public GameObjectBuilder<T> useRectangularBody(float x, float y, float width, float height, float mass) {
        bodySupplier = () -> new Body(new Rectangle(x, y, width, height), mass);
        return this;
    }

    public Body getBody() {
        return bodySupplier.get();
    }

    public GameObjectBuilder<T> setCurrentVelocity(Point velocity) {
        currentVelocity = new Vector(velocity);
        return this;
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

    public GameObjectBuilder<T> withTextureSupplier(Supplier<BufferedImage> textureSupplier) {
        this.textureSupplier = textureSupplier;
        return this;
    }


    public Texture getTexture() {

        return new Texture() {

            private BufferedImage image;

            @Override
            public BufferedImage getImage() {
                if (image == null) {
                    image = textureSupplier.get();
                }
                return image;
            }

            @Override
            public BufferedImage getFallbackImage() {
                return null;
            }

        };

    }


    public GameObjectBuilder<T> withFullHealth(float maxHealth, float regenerationRate) {

        // This method ensures that any GameObject constructed by this builder has
        // an individual health object.
        healthSupplier = () -> new Health(maxHealth, maxHealth, regenerationRate);

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
