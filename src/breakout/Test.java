package breakout;


import breakout.game.AbstractGame;
import breakout.game.GameObjectBody;
import breakout.game.animation.Animation;
import breakout.game.animation.AnimationBuilder;
import breakout.game.api.Ball;
import breakout.game.api.Brick;
import breakout.game.api.Level;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;
import breakout.game.texture.AnimatedTexture;
import breakout.game.texture.Sprite;
import breakout.game.texture.SpriteDirectMapStrategy;
import breakout.physics.Gravity;
import breakout.physics.World;
import breakout.physics.collision.CollisionEvent;
import breakout.physics.collision.CollisionListener;
import breakout.physics.geometry.Vector;
import lighthouse.GreatestLevel;
import lighthouse.RainbowBall;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Test {

    public static void main(String[] args) {

        Level level = new GreatestLevel();

        World w = new World(640, 480, new Gravity(new Vector(0, 1)));
        Ball b = level.getBall();
        b.getBody().setPosition(100, 100);
        w.addBody(b.getBody());



        Brick brick = level.getBricks().get(0).create();
        brick.getBody().setPosition(16,16);
        w.addBody(brick.getBody());


        AbstractGame g = new AbstractGame() {
            @Override
            public void update(int dt, GameState state) {

                Keyboard keyboard = state.getKeyboard();

                if (keyboard.isKeyPressed(KeyEvent.VK_W)) {
                    b.getBody().move(0,-1 * 32 / dt);
                }

                if (keyboard.isKeyPressed(KeyEvent.VK_A)) {
                    b.getBody().move(-1 * 32 / dt,0);
                }

                if (keyboard.isKeyPressed(KeyEvent.VK_S)) {
                    b.getBody().move(0,32 / dt);
                }

                if (keyboard.isKeyPressed(KeyEvent.VK_D)) {
                    b.getBody().move(32 / dt,0);
                }

            }
        };

        g.getWindow().addKeyListener(new Keyboard());

        // TODO layer system?
        g.getGameState().addGameObject(brick);
        g.getGameState().addGameObject(b);

        b.getBody().addCollisionListener((CollisionListener<GameObjectBody>) event -> {
            if (event.getTarget().getGameObject() instanceof Brick) {
                g.getGameState().removeGameObject(event.getTarget().getGameObject());
            }
        });

        Thread t = new Thread(g);
        t.start();


    }

}

