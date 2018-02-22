package breakout;


import breakout.game.AbstractGame;
import breakout.game.animation.Animation;
import breakout.game.animation.AnimationBuilder;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;
import breakout.game.texture.AnimatedTexture;
import breakout.game.texture.Sprite;
import breakout.game.texture.SpriteDirectMapStrategy;
import lighthouse.RainbowBall;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;


public class Test {

    public static void main(String[] args) {

        RainbowBall b = new RainbowBall();

        try {

            BufferedImage spriteImage = ImageIO.read(RainbowBall.class.getResource("resources/sprite.png"));

            Sprite sprite = new Sprite(spriteImage, new SpriteDirectMapStrategy() {{
                setMapping("Frame 1", 0, 0, 100, 100);
                setMapping("Frame 2", 100, 0, 100, 100);
                setMapping("Frame 3", 200, 0, 100, 100);
            }});

            Animation anim = (new AnimationBuilder(1000))
                    .useSprite(sprite)
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

