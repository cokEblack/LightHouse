package lighthouse;

import breakout.game.api.*;

import java.util.HashSet;
import java.util.Set;

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

        @Override
        public Paddle create() {
            return new GloriousPaddle();
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
