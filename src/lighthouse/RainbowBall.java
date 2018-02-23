package lighthouse;

import breakout.game.GameObjectBody;
import breakout.game.animation.Animation;
import breakout.game.animation.AnimationBuilder;
import breakout.game.api.AbstractGameObject;
import breakout.game.api.Ball;
import breakout.game.state.GameState;
import breakout.game.texture.AnimatedTexture;
import breakout.game.texture.Sprite;
import breakout.game.texture.SpriteDirectMapStrategy;
import breakout.game.texture.Texture;
import breakout.physics.Body;
import breakout.physics.geometry.Rectangle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class RainbowBall extends AbstractGameObject implements Ball {

    {

        setBody(new GameObjectBody(this, new Body(new Rectangle(0, 0, 100, 100), 1)));

        try {

            BufferedImage spriteImage = ImageIO.read(GreatestLevel.class.getResource("resources/sprite.png"));

            Sprite sprite = new Sprite(spriteImage, new SpriteDirectMapStrategy() {{
                setMapping("Frame 1", 0, 0, 100, 100);
                setMapping("Frame 2", 100, 0, 100, 100);
                setMapping("Frame 3", 200, 0, 100, 100);
            }});

            Animation anim = (new AnimationBuilder(1000))
                    .useSprite(sprite)
                    .build();

            setTexture(new AnimatedTexture(anim));


        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
        setTexture(new Texture() {

            private BufferedImage image = null;
            private String path = "resources/test.png";

            @Override
            public BufferedImage getImage() {

                if (image == null) {

                    URL url = RainbowBall.class.getResource(path);

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

                    try {
                        image = ImageIO.read(url);
                    } catch (IOException | IllegalArgumentException exception) {
                        System.out.printf("Failed loading texture \"%s\".\n", url == null ? path : url);
                        image = getFallbackImage();
                    }
                }

                return image;

            }

            @Override
            public BufferedImage getFallbackImage() {

                if (image == null) {

                    image = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
                    Graphics g = image.getGraphics();
                    g.setColor(Color.BLACK);
                    g.drawArc(0,0, 100, 100, 0, 360);

                }

                return image;

            }
        });
        */

    }

    @Override
    public void update(int dt, GameState state) {

    }



}
