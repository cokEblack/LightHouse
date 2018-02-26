package lighthouse;

import breakout.game.GameObjectBuilder;
import breakout.game.api.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GreatestLevel implements Level {

    private class BallFactory implements GameObjectFactory<Ball> {

        @Override
        public Ball create() {
            return new RainbowBall();
        }

    }

    private class BrickFactory implements GameObjectFactory<Brick> {

        @Override
        public Brick create() {
            return new SolidBrick(16, 16);
        }

    }

    private class PaddleFactory implements GameObjectFactory<Paddle> {

        private GameObjectBuilder<Paddle> builder;

        {
            builder = (new GameObjectBuilder<Paddle>(LessGloriousPaddle.class))
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
        }

        @Override
        public Paddle create() {
            return (Paddle) builder.build();
        }

    }

    @Override
    public GameObjectFactory<Paddle> createPaddleFactory() {
        return new PaddleFactory();
    }

    @Override
    public GameObjectFactory<Ball> createBallFactory() {
        return new BallFactory();
    }

    @Override
    public GameObjectFactory<Brick> createBrickFactory() {
        return new BrickFactory();
    }
}
