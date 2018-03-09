package area52;

import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.AbstractGameObject;
import breakout.game.api.Ball;
import breakout.game.state.GameState;

public class RainbowBall extends AbstractGameObject implements Ball {

    public RainbowBall() {
        super();
    }

    public RainbowBall(GameObjectBuilder builder) {
        super(builder);
    }

}
