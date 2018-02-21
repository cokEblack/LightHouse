package lighthouse;

import breakout.game.api.AbstractGameObject;
import breakout.game.api.Ball;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;
import breakout.physics.Body;
import breakout.physics.geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RainbowBall extends AbstractGameObject implements Ball {

    {

        setBody(new Body(new Rectangle(0, 0, 100, 100), 1));

        setTexture(new Texture() {

            private BufferedImage image = null;
            private String path = "resources/test.png";

            @Override
            public BufferedImage getImage() {

                if (image == null) {

                    URL url = RainbowBall.class.getResource(path);

                    try {
                        image = ImageIO.read(url);
                    } catch (IOException | IllegalArgumentException exception) {
                        System.out.printf("Failed loading texture \"%s\".\n", url == null ? path : url);
                        image = getFallbackImage();
                    }
                }

                return image;

            }

            @Override
            public BufferedImage getFallbackImage() {

                if (image == null) {

                    image = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics g = image.getGraphics();
                    g.setColor(Color.BLACK);
                    g.drawArc(0,0, 100, 100, 0, 360);

                }

                return image;

            }
        });

    }

    @Override
    public void update(int dt, GameState state) {

    }



}
