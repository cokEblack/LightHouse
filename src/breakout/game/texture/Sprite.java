package breakout.game.texture;

import newton.geometry.Rectangle;

import java.awt.image.BufferedImage;

/**
 * The sprite is used to get sub-image of an sprite image file.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Sprite {

    /** The sprite image */
    private BufferedImage image;

    /** The map strategy used to extract the sub-images */
    private SpriteMapStrategy mapStrategy;

    /**
     * Creates a sprite with an image and a map strategy.
     *
     * @param image A sprite image
     * @param mapStrategy A map strategy
     */
    public Sprite(BufferedImage image, SpriteMapStrategy mapStrategy) {
        this.image = image;
        this.mapStrategy = mapStrategy;
    }

    /**
     * Creates a sprite with an image and no specific map strategy.
     *
     * @param image A sprite image
     */
    public Sprite(BufferedImage image) {
        this(image, null);
    }

    /**
     * Returns the map strategy used by the sprite.
     *
     * @return The map strategy used by the sprite.
     */
    public SpriteMapStrategy getMapStrategy() {
        return mapStrategy;
    }

    /**
     * Returns the sprite image queried by a name.
     *
     * @param name A name related to a sub-image
     * @return A sub-image
     */
    public BufferedImage getImage(String name) {

        if (mapStrategy == null) {
            throw new IllegalStateException("A SpriteMapStrategy must be provided to get images using this method.");
        }

        return getImage(mapStrategy.getMapping(name));

    }

    /**
     * Directly create a sub-image with a position and a dimension.
     *
     * @param x A x-coordinate
     * @param y A y-coordinate
     * @param width A width
     * @param height A height
     * @return A sub-image
     */
    public BufferedImage getImage(float x, float y, float width, float height) {
        return getImage((int) x, (int) y, (int) width, (int) height);
    }

    /**
     * Directly create a sub-image with a position and a dimension.
     *
     * @param x A x-coordinate
     * @param y A y-coordinate
     * @param width A width
     * @param height A height
     * @return A sub-image
     */
    public BufferedImage getImage(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    /**
     * Directly create a sub-image with a rectangle.
     *
     * @param rect A rectangle
     * @return A sub-image
     */
    public BufferedImage getImage(Rectangle rect) {
        return getImage(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

}
