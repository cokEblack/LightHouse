package area52;

import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.animation.AnimationBuilder;
import breakout.game.api.*;
import breakout.game.texture.AnimatedTexture;
import breakout.game.texture.Sprite;
import breakout.game.texture.SpriteDirectMapStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GreatestLevel implements Level {

    private GameObjectBuilder<Ball> ballBuilder;
    private GameObjectBuilder<Brick> brickBuilder;
    private GameObjectBuilder<Paddle> paddleBuilder;

    {

        ballBuilder = (new GameObjectBuilder<Ball>(RainbowBall.class))
                .withFullHealth(10)
                .useCircularBody(0, 0, 50, 1)
                .setCurrentVelocity(0f, 0.2f)
                .setMaximumVelocity(0.2f, 0.2f)
                .ignoreGravity(true);

        try {

            BufferedImage spriteImage = ImageIO.read(GreatestLevel.class.getResource("resources/sprite.png"));

            Sprite sprite = new Sprite(spriteImage, new SpriteDirectMapStrategy() {{
                setMapping("Frame 1", 0, 0, 100, 100);
                setMapping("Frame 2", 100, 0, 100, 100);
                setMapping("Frame 3", 200, 0, 100, 100);
            }});

            AnimationBuilder animationBuilder = (new AnimationBuilder(1000))
                    .useSprite(sprite);

            ballBuilder.withTextureSupplier(() -> new AnimatedTexture(animationBuilder.build()));


        } catch (Exception e) {
            e.printStackTrace();
        }

        brickBuilder = (new GameObjectBuilder<Brick>(SolidBrick.class))
                .useRectangularBody(16, 16, 64, 16, 1)
                .withFullHealth(10)
                .withTextureSupplier((image) -> {

                    Graphics2D g = (Graphics2D) image.getGraphics();

                    // Draw rectangle
                    g.setColor(new Color(0x46b29d));
                    g.fillRect(0, 0, image.getWidth(), image.getHeight());

                    // Draw stroke
                    Stroke oldStroke = g.getStroke();
                    g.setColor(Color.BLACK);
                    g.setStroke(new BasicStroke(1));
                    g.drawRect(0, 0, image.getWidth(), image.getHeight());
                    g.setStroke(oldStroke);

                });

        paddleBuilder = (new GameObjectBuilder<Paddle>(LessGloriousPaddle.class))
                .useRectangularBody(0, 0, 100, 20, 1)
                .setMaximumVelocity(0.2f, 0.2f)
                .withFullHealth(10)
                .withTextureSupplier((image) -> {
                    Graphics2D g = (Graphics2D) image.getGraphics();
                    g.setColor(Color.CYAN);
                    g.fillRect(0,0, image.getWidth(), image.getHeight());
                });

    }

    @Override
    public GameObjectFactory<Paddle> createPaddleFactory() {
        return () -> paddleBuilder.build();
    }

    @Override
    public GameObjectFactory<Ball> createBallFactory() {
        return () -> ballBuilder.build();
    }

    @Override
    public GameObjectFactory<Brick> createBrickFactory() {
        return () -> brickBuilder.build();
    }
}
