package breakout.game.texture;

import java.awt.image.BufferedImage;

public class ImageTexture implements Texture {

    private BufferedImage image;

    public ImageTexture(BufferedImage image) {
        this.image = image;
    }

    @Override
    public BufferedImage getImage() {
        return image;
    }

}
