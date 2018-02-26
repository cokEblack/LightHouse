package lighthouse;

import breakout.game.GameObjectBody;
import breakout.game.Health;
import breakout.game.api.AbstractGameObject;
import breakout.game.api.Brick;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;
import newton.physics.Body;
import newton.geometry.Rectangle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SolidBrick extends AbstractGameObject implements Brick {

    {

        setName(SolidBrick.class.getName());
        setHealth(new Health(100,100, 0));

        setTexture(new Texture() {

            private BufferedImage image;

            @Override
            public BufferedImage getImage() {

                if (image == null) {

                    image = new BufferedImage(
                            (int) getBody().getShape().getBounds().getWidth(),
                            (int) getBody().getShape().getBounds().getHeight(),
                            BufferedImage.TYPE_4BYTE_ABGR
                    );

                    Graphics2D g = (Graphics2D) image.getGraphics();

                    g.setColor(new Color(0x46b29d));
                    g.fillRect(0, 0, image.getWidth(), image.getHeight());

                    Stroke oldStroke = g.getStroke();
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(1));
                    g.drawRect(0, 0, image.getWidth(), image.getHeight());
                    g.setStroke(oldStroke);


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

    public SolidBrick(float x, float y) {
        setBody(new GameObjectBody(this, new Body(new Rectangle(x, y, 64, 16),0)));
    }

    @Override
    public synchronized void update(int dt, GameState state) {
    }

}
