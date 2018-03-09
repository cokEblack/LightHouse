package area52;

import breakout.game.gameobject.GameObjectBody;
import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.AbstractGameObject;
import breakout.game.api.Paddle;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;
import newton.geometry.Rectangle;
import newton.geometry.Vector;
import newton.physics.Body;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GloriousPaddle extends AbstractGameObject implements Paddle {

    {
        setBody(new GameObjectBody(this, new Body(new Rectangle(0, 0, 100, 20), 1)));

        getBody().setMaximumVelocity(new Vector(0.2f, 0.2f));

        setTexture(new Texture() {

            private BufferedImage image;

            @Override
            public BufferedImage getImage() {

                if (image == null) {
                    image = new BufferedImage((int) getBody().getWidth(), (int) getBody().getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D g = (Graphics2D) image.getGraphics();
                    g.setColor(Color.CYAN);
                    g.fillRect(0,0, image.getWidth(), image.getHeight());
                    g.dispose();
                }

                return image;

            }

        });

    }

    public GloriousPaddle() {
        super();
    }

    public GloriousPaddle(GameObjectBuilder builder) {
        super(builder);
    }

    @Override
    public synchronized void update(int dt, GameState state) {

        super.update(dt, state);

        Keyboard keyboard = state.getKeyboard();

        if (keyboard.isKeyPressed(KeyEvent.VK_A)) {
            getBody().setVelocity(getBody().getVelocity().setX(-1 * 4f / dt));
        } else if (keyboard.isKeyPressed(KeyEvent.VK_D)) {
            getBody().setVelocity(getBody().getVelocity().setX(4f / dt));
        } else {
            getBody().setVelocity(getBody().getVelocity().setX(0));
        }

    }
}
