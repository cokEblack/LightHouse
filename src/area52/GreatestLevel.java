package area52;

import breakout.game.Game;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.gameobject.*;
import breakout.game.animation.AnimationBuilder;
import breakout.game.api.*;
import breakout.game.texture.*;
import lighthouse.LighthousePlugin;
import lighthouse.LighthouseRendererThread;
import lighthouse.renderer.LighthouseRenderer;
import newton.geometry.Circle;
import newton.geometry.Vector;
import newton.physics.Body;
import newton.physics.World;
import newton.physics.collision.CollisionEvent;
import newton.physics.collision.CollisionListener;
import newton.physics.collision.WorldBorderCollisionAdapter;
import unitframeplugin.UnitFrameGameObjectProperty;
import unitframeplugin.UnitFramePlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static newton.util.Math.clamp;

public class GreatestLevel implements Level {

    private GameObjectBuilder<Ball> ballBuilder;
    private GameObjectBuilder<Brick> brickBuilder;
    private GameObjectBuilder<Paddle> paddleBuilder;
    private GameListener gameListener;

    private Function<GameObject, PropertyChangeListener> healthChangeListenerSupplier;

    {
        healthChangeListenerSupplier = (gameObject) -> {
            return (event) -> {

                Logger.getLogger(Game.class.getName()).info(() -> {

                    float newValue = (float) event.getNewValue();
                    float oldValue = (float) event.getOldValue();

                    float dh = newValue - oldValue;

                    if (dh < 0) {

                        return String.format(
                                "%s (%.2f, %.2f) has received %.2f damage.",
                                gameObject.getName(),
                                newValue,
                                gameObject.getHealth().getMaximumValue(),
                                Math.abs(dh)
                        );

                    } else if (dh > 0) {

                        return String.format(
                                "%s (%.2f, %.2f) restored %.2f health points.",
                                gameObject.getName(),
                                newValue,
                                gameObject.getHealth().getMaximumValue(),
                                Math.abs(dh)
                        );

                    } else {
                        return "Nothing happened to " + gameObject.getName() + " (%.2f, %.2f)";
                    }

                });

            };
        };
    }

    {

        // Setup ball builder

        ballBuilder = (new GameObjectBuilder<Ball>(RainbowBall.class))
                .withFullHealth(10)
                .useCircularBody(0, 0, 16, 1)
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


        // Setup brick builder

        brickBuilder = (new GameObjectBuilder<Brick>(SolidBrick.class))
                .useRectangularBody(0, 0, 32, 32, 1)
                .withFullHealth(10);


        try {
            BufferedImage spriteImage = ImageIO.read(GreatestLevel.class.getResource("resources/bdc_cobblestone01.png"));
            brickBuilder.withTextureSupplier(() -> new ImageTexture(spriteImage));
        } catch (IOException exception) {
            brickBuilder.withTextureSupplier((image) -> {

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
        }

        // Setup paddle builder

        paddleBuilder = (new GameObjectBuilder<Paddle>(LessGloriousPaddle.class))
                .useRectangularBody(0, 0, 128, 32, 1)
                .setMaximumVelocity(0.2f, 0.2f)
                .withFullHealth(10)
                .withTextureSupplier((image) -> {
                    Graphics2D g = (Graphics2D) image.getGraphics();
                    g.setColor(Color.CYAN);
                    g.fillRect(0,0, image.getWidth(), image.getHeight());
                });



        gameListener = new GameListener() {

            private IgniAbility igni;

            @Override
            public void onCreate(GameLoopEvent gameLoopEvent) {

                Game game = gameLoopEvent.getGame();
                Level level = game.getLevel();

                World world = new World(32 * 28, 32 * 14);
                world.setGravity(0, 0.0000075f);
                game.getGameState().setWorld(world);

                Paddle paddle = level.createPaddleFactory().create();
                paddle.getBody().setY(480 - paddle.getBody().getShape().getBounds().getHeight() - 32);
                paddle.getHealth().setRegenerationRate(0.0001f);
                paddle.getHealth().addPropertyChangeListener(healthChangeListenerSupplier.apply(paddle));
                paddle.addGameObjectListener(new GameObjectListener() {

                    @Override
                    public void onDestroy(GameObjectEvent event) {
                        game.getLogger().info(() -> paddle.getName() + " has been destroyed.");
                    }

                });
                GameObjectResource paddleAdrenaline = new Adrenaline(10, 10, 0.0002f);
                paddle.setAttribute(Adrenaline.class, paddleAdrenaline);
                paddle.setData(UnitFrameGameObjectProperty.UNIT_FRAME_SECONDARY_RESOURCE, paddleAdrenaline);

                Ball ball = level.createBallFactory().create();
                ball.getBody().setPosition(100, 100);
                ball.setWeapon(new GenericWeapon(3));

                GameObjectFactory<Brick> brickFactory = level.createBrickFactory();

                for (int i = 0, n = 4; i < n; i++) {
                    Brick brick = brickFactory.create();
                    brick.getBody().setPosition(32 + brick.getBody().getWidth() * i, 32);
                    brick.getHealth().addPropertyChangeListener(healthChangeListenerSupplier.apply(brick));
                    game.getGameState().addGameObject(brick);
                }

                game.getGameState().addGameObject(ball);
                game.getGameState().addGameObject(paddle);

                // Remove any brick, that was hit by the ball.
                ball.getBody().addCollisionListener((CollisionListener<Body>) event -> {

                    if (!(event.getTarget() instanceof GameObjectBody)) {
                        return;
                    }

                    GameObject brick = ((GameObjectBody) event.getTarget()).getGameObject();

                    if (brick instanceof Brick) {

                        ball.getBody().setVelocity(ball.getBody().getVelocity().scale(-1));

                        // The brick is removed from the game, if it was hit by the ball.
                        brick.damage(ball.getWeapon().getDamage());

                    }

                });

                // If the ball hits the paddle in different points, the angle adjusts accordingly
                // which offers more control over the ball.
                ball.getBody().addCollisionListener((CollisionListener<Body>) event -> {

                    if (event.getTarget() instanceof GameObjectBody && ((GameObjectBody) event.getTarget()).getGameObject() == paddle) {

                        // The ball's center x-coordinate
                        float cx = ((Circle) ball.getBody().getShape()).getCenterX();
                        // Half of the paddle's length
                        float phw = paddle.getBody().getWidth() / 2;
                        // Center x-coordinate of the paddle
                        float pmx = paddle.getBody().getX() + phw;

                        // The difference of the ball's center must be in the range
                        // of [-phw, phw] to make a proper hit. If the ball hits a
                        // vertex, it is treated as proper hit (because of the clamp).
                        float x = clamp(-1 * phw, phw, cx - pmx);

                        // Calculate an angle by which the trajectory can be rotated.
                        float angle = (x / phw) * ((float) Math.toRadians(30));

                        Vector bv = ball.getBody().getVelocity();

                        float direction = Math.signum(x) * Math.signum(bv.getX());

                        ball.getBody().setVelocity(bv.scaleX(direction).scaleY(-1).rotate(angle));

                        paddle.damage(ball.getWeapon().getDamage());

                    }

                });

                ball.getBody().addCollisionListener(new WorldBorderCollisionAdapter(world) {

                    @Override
                    public void onCollisionWithTopBorder(CollisionEvent event) {

                        game.getLogger().info(() -> String.format("%s hit world's top border.", ball.getClass().getName()));

                        Vector velocity = ball.getBody().getVelocity();
                        ball.getBody().setVelocityY(-1 * velocity.getY());

                    }

                    @Override
                    public void onCollisionWithRightBorder(CollisionEvent event) {

                        game.getLogger().info(() -> String.format("%s hit world's right border.", ball.getClass().getName()));

                        Vector velocity = ball.getBody().getVelocity();
                        ball.getBody().setVelocityX(-1 * velocity.getX());

                    }

                    @Override
                    public void onCollisionWithBottomBorder(CollisionEvent event) {

                        game.getLogger().info(() -> String.format("%s hit world's bottom border.", ball.getClass().getName()));

                        Vector velocity = ball.getBody().getVelocity();
                        ball.getBody().setVelocityY(-1 * velocity.getY());

                    }

                    @Override
                    public void onCollisionWithLeftBorder(CollisionEvent event) {

                        game.getLogger().info(() -> String.format("%s hit world's left border.", ball.getClass().getName()));

                        Vector velocity = ball.getBody().getVelocity();
                        ball.getBody().setVelocityX(-1 * velocity.getX());

                    }

                });

                QuenAbility quen = new QuenAbility(paddle);
                DrinkSwallowAbility swallow = new DrinkSwallowAbility(paddle);
                igni = new IgniAbility(paddle, game.getGameState());

                game.getWindow().addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyReleased(KeyEvent event) {
                        if (event.getKeyCode() != KeyEvent.VK_1) return;
                        quen.apply(null);
                    }

                });

                game.getWindow().addKeyListener(new KeyAdapter() {

                    @Override
                    public void keyReleased(KeyEvent event) {
                        if (event.getKeyCode() != KeyEvent.VK_2) return;
                        swallow.apply(null);
                    }

                });



                game.addPlugin(new LighthousePlugin());

                // Setup UnitFramePlugin and display paddle
                UnitFramePlugin unitFramePlugin = new UnitFramePlugin();
                game.addPlugin(unitFramePlugin);
                unitFramePlugin.getUnitFrame().setUnit(paddle);


            }


            @Override
            public void onUpdate(GameLoopEvent event) {

                if (event.getGameState().getKeyboard().isKeyPressed(KeyEvent.VK_3)) {
                    igni.apply(null);
                }

            }

        };

    }

    @Override
    public GameObjectFactory<Paddle> createPaddleFactory() {
        return () -> {
            try {
                return paddleBuilder.build();
            } catch (InstantiationException exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    @Override
    public GameObjectFactory<Ball> createBallFactory() {
        return () -> {
            try {
                return ballBuilder.build();
            } catch (InstantiationException exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    @Override
    public GameObjectFactory<Brick> createBrickFactory() {
        return () -> {
            try {
                return brickBuilder.build();
            } catch (InstantiationException exception) {
                throw new RuntimeException(exception);
            }
        };
    }

    @Override
    public GameListener getGameListener() {
        return gameListener;
    }

}
