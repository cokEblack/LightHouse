package lighthouse;

import breakout.game.GameObjectBuilder;
import breakout.game.api.AbstractGameObject;
import breakout.game.api.Paddle;
import breakout.game.io.Keyboard;
import breakout.game.state.GameState;

import java.awt.event.KeyEvent;

public class LessGloriousPaddle extends AbstractGameObject implements Paddle {

    public LessGloriousPaddle() {
        super();
    }

    public LessGloriousPaddle(GameObjectBuilder builder) {
        super(builder);
    }

    @Override
    public synchronized void update(int dt, GameState state) {

        Keyboard keyboard = state.getKeyboard();

        if (keyboard.isKeyPressed(KeyEvent.VK_A)) {
            getBody().setVelocity(getBody().getVelocity().setX(-1 * 4f / dt));
        } else if (keyboard.isKeyPressed(KeyEvent.VK_D)) {
            getBody().setVelocity(getBody().getVelocity().setX(4f / dt));
        } else {
            getBody().setVelocity(getBody().getVelocity().setX(0));
        }

        getBody().move(dt);


    }

}
