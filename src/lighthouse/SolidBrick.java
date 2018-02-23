package lighthouse;

import breakout.game.GameObjectBody;
import breakout.game.api.AbstractGameObject;
import breakout.game.api.Brick;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;
import breakout.physics.Body;
import breakout.physics.geometry.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SolidBrick extends AbstractGameObject implements Brick{

    {

        setName(SolidBrick.class.getName());
        setBody(new GameObjectBody(this, new Body(new Rectangle(16, 16, 64, 16),0)));

        setTexture(new Texture() {

            private BufferedImage image;

            @Override
            public BufferedImage getImage() {

                if (image == null) {

                    image = new BufferedImage(
                            (int) getBody().getShape().getWidth(),
                            (int) getBody().getShape().getHeight(),
                            BufferedImage.TYPE_4BYTE_ABGR
                    );

                    Graphics g = image.getGraphics();
                    g.setColor(new Color(0x46b29d));
                    g.fillRect(0, 0, image.getWidth(), image.getHeight());

                    g.dispose();

                }

                return image;

            }

            @Override
            public BufferedImage getFallbackImage() {
                return null;
            }
        });
    }

    @Override
    public void update(int dt, GameState state) {}

}
