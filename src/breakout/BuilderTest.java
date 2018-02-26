package breakout;

import breakout.game.GameObjectBuilder;
import breakout.game.api.Paddle;
import lighthouse.GloriousPaddle;
import lighthouse.LessGloriousPaddle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BuilderTest {

    public static void main(String[] args) {

        /*
        GameObjectBuilder<Paddle> paddleBuilder = new GameObjectBuilder<>(GloriousPaddle.class);
        Paddle paddle = (Paddle) paddleBuilder.withFullHealth(10).build();
        */

        GameObjectBuilder<Paddle> paddleBuilder = (new GameObjectBuilder<Paddle>(LessGloriousPaddle.class))
                .useRectangularBody(0, 0, 100, 20, 1)
                .setMaximumVelocity(0.2f, 0.2f)
                .withFullHealth(10)
                .withTextureSupplier(() -> {

                    BufferedImage image = new BufferedImage(100, 20, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics2D g = (Graphics2D) image.getGraphics();
                    g.setColor(Color.CYAN);
                    g.fillRect(0,0, image.getWidth(), image.getHeight());
                    g.dispose();

                    return image;

                });
        Paddle paddle = (Paddle) paddleBuilder.build();

        System.out.println(paddle.getHealth().getCurrentValue());

    }

}
