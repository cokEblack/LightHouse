package area52;

import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.AbstractGameObject;
import breakout.game.api.Brick;
import breakout.game.state.GameState;

public class SolidBrick extends AbstractGameObject implements Brick {

    public SolidBrick() {
        super();
    }

    public SolidBrick(GameObjectBuilder builder) {
        super(builder);
    }

    @Override
    public synchronized void update(int dt, GameState state) {
    }

}
