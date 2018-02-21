package breakout.game.texture;

import java.awt.image.BufferedImage;

public class Sprite {

    private BufferedImage image;

    public Sprite(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage(float x, float y, float width, float height) {
        return getImage((int) x, (int) y, (int) width, (int) height);
    }

    public BufferedImage getImage(int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

}
