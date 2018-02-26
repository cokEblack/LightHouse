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
import newton.geometry.Circle;
import newton.geometry.Vector;
import newton.physics.Body;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class RainbowBall extends AbstractGameObject implements Ball {

    {

        setBody(new GameObjectBody(this, new Body(new Circle(0, 0, 50), 1)));
        getBody().setMaximumVelocity(new Vector(0.2f, 0.2f));
        getBody().ignoreGravity(true);
        getBody().addVelocity(0, 0.2f);

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

    }

    @Override
    public synchronized void update(int dt, GameState state) {

        getBody().accelerate(dt);
        getBody().move(dt);

    }



}
