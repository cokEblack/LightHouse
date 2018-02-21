package breakout;


import breakout.game.AbstractGame;
import breakout.game.animation.Animation;
import breakout.game.animation.AnimationBuilder;
import breakout.game.animation.AnimationFrame;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;
import breakout.game.texture.AnimatedTexture;
import breakout.game.texture.Sprite;
import lighthouse.RainbowBall;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Test {

    public static void main(String[] args) {

        RainbowBall b = new RainbowBall();

        try {

            BufferedImage spriteImage = ImageIO.read(RainbowBall.class.getResource("resources/sprite.png"));

            Sprite sprite = new Sprite(spriteImage);
            Animation anim = (new AnimationBuilder(1000))
                    .addFrame(new AnimationFrame(sprite.getImage(0, 0, 100, 100)))
                    .addFrame(new AnimationFrame(sprite.getImage(100, 0, 100, 100)))
                    .addFrame(new AnimationFrame(sprite.getImage(200, 0, 100, 100)))
                    .build();

            b.setTexture(new AnimatedTexture(anim));


        } catch (Exception e) {
            e.printStackTrace();
        }

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


        g.getGameState().addGameObject(b);

        Thread t = new Thread(g);
        t.start();


    }

}

