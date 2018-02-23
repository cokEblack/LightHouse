package lighthouse;

import breakout.game.api.Ball;
import breakout.game.api.Brick;
import breakout.game.api.BrickFactory;
import breakout.game.api.Level;

import java.util.ArrayList;
import java.util.List;

public class GreatestLevel implements Level {

    private List<BrickFactory<? extends Brick>> brickFactories = new ArrayList<>();

    {

        brickFactories.add(SolidBrick::new);

    }

    @Override
    public Ball getBall() {
        return new RainbowBall();
    }

    @Override
    public List<BrickFactory<? extends Brick>> getBricks() {
        return brickFactories;
    }
}
