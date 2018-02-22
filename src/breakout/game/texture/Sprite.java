package breakout.game.texture;

import breakout.physics.geometry.Rectangle;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage image;
    private SpriteMapStrategy mapStrategy;

    public Sprite(BufferedImage image, SpriteMapStrategy mapStrategy) {
        this.image = image;
        this.mapStrategy = mapStrategy;
    }

    public Sprite(BufferedImage image) {
        this(image, null);
    }

    public SpriteMapStrategy getMapStrategy() {
        return mapStrategy;
    }

    public BufferedImage getImage(String name) {

        if (mapStrategy == null) {
            throw new IllegalStateException("A SpriteMapStrategy must be provided to get images using this method.");
        }

        return getImage(mapStrategy.getMapping(name));

    }

    public BufferedImage getImage(float x, float y, float width, float height) {
        return getImage((int) x, (int) y, (int) width, (int) height);
    }

    public BufferedImage getImage(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    public BufferedImage getImage(Rectangle rect) {
        return getImage(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
    }

}
