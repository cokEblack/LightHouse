package breakout;


import breakout.game.AbstractGame;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.gameobject.GameObjectBody;
import breakout.game.api.*;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;
import lighthouse.LighthouseRendererThread;
import newton.geometry.Circle;
import newton.geometry.Vector;
import newton.physics.Body;
import newton.physics.World;
import newton.physics.collision.CollisionEvent;
import newton.physics.collision.CollisionListener;
import newton.physics.collision.WorldBorderCollisionAdapter;


import static newton.util.Math.clamp;

public class Test {

    public static void main(String[] args) {

        Level level = null;
        Class<? extends Level> levelClass = null;

        try {
            // TODO This class should either be given by the arguments provided to this program or a graphical interface.
            // ... if this is fully implemented, this program is solely a player for levels.
            levelClass = (Class<? extends Level>) Class.forName("area52.GreatestLevel");
        } catch (ClassNotFoundException exception) {
            System.err.println("The level cannot be found.");
        }

        try {
            level = levelClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            System.err.println("Each level must implement a default constructor with no formal parameters.");
            exception.printStackTrace();
        }


        World world = new World(0, 0, 640, 480);
        world.setGravity(0, 0.0000075f);

        Paddle paddle = level.createPaddleFactory().create();
        paddle.getBody().setY(480 - paddle.getBody().getShape().getBounds().getHeight() - 32);
        world.addBody(paddle.getBody());

        Ball ball = level.createBallFactory().create();
        ball.getBody().setPosition(100, 100);
        world.addBody(ball.getBody());

        GameObjectFactory<Brick> brickFactory = level.createBrickFactory();

        AbstractGame game = new AbstractGame() {

            @Override
            public void create() {

                getWindow().addKeyListener(new Keyboard());

                // TODO layer system?

                for (int i = 0, n = 4; i < n; i++) {
                    Brick brick = brickFactory.create();
                    brick.getBody().setPosition(16 + brick.getBody().getWidth() * i, 16);
                    world.addBody(brick.getBody());
                    getGameState().addGameObject(brick);
                }

                getGameState().addGameObject(ball);
                getGameState().addGameObject(paddle);

            }

            @Override
            public synchronized void update(int dt, GameState state) {
                super.update(dt, state);
            }

        };


        // TODO use the POST_CREATE event of the game to determine which color is displayed on the lighthouse
        LighthouseRendererThread lighthouse = new LighthouseRendererThread();
        game.addGameListener(new GameListener() {

            @Override
            public void onPostRender(GameLoopEvent event) {
                // TODO Enqueue a copy of the game state
                lighthouse.enqueueGameState(event.getGameState());
            }

        });



        // Remove any brick, that was hit by the ball.
        ball.getBody().addCollisionListener((CollisionListener<Body>) event -> {

            if (!(event.getTarget() instanceof GameObjectBody)) {
                return;
            }

            GameObject brick = ((GameObjectBody) event.getTarget()).getGameObject();

            if (brick instanceof Brick) {

                // TODO The trajectory must be adjusted correctly (regarding direction, ...)
                ball.getBody().setVelocity(ball.getBody().getVelocity().scale(-1));

                // The brick is removed from the game, if it was hit by the ball.
                game.getGameState().removeGameObject(brick);

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

                // TODO The trajectory must be adjusted correctly (regarding direction, ...)
                ball.getBody().setVelocity(ball.getBody().getVelocity().scale(-1).rotate(angle));

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


        lighthouse.start();

        Thread t = new Thread(game);
        t.start();


    }

}

