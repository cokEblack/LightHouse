package breakout.game.animation;

import java.awt.image.BufferedImage;

public class AnimationFrame {

    private BufferedImage image;

    public AnimationFrame(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
