package breakout;

import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.api.Paddle;
import area52.LessGloriousPaddle;

import java.awt.*;

public class BuilderTest {

    public static void main(String[] args) {

        /*
        GameObjectBuilder<Paddle> paddleBuilder = new GameObjectBuilder<>(GloriousPaddle.class);
        Paddle paddle = (Paddle) paddleBuilder.withFullHealth(10).build();
        */

        GameObjectBuilder<Paddle> paddleBuilder = (new GameObjectBuilder<Paddle>(LessGloriousPaddle.class))
                .setName("__xXx$$InfernoPaddleOfDeath$$xXx__")
                .useRectangularBody(0, 0, 100, 20, 1)
                .setMaximumVelocity(0.2f, 0.2f)
                .withFullHealth(10)
                .withTextureSupplier((image) -> {
                    Graphics g = image.getGraphics();
                    g.setColor(Color.CYAN);
                    g.fillRect(0,0, image.getWidth(), image.getHeight());
                });
        Paddle paddle = (Paddle) paddleBuilder.build();

        System.out.println(paddle.getName() + " " + paddle.getHealth().getCurrentValue());

    }

}
