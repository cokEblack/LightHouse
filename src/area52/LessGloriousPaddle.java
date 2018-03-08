package area52;

import breakout.game.gameobject.GameObjectBuilder;
import breakout.game.gameobject.AbstractGameObject;
import breakout.game.api.Paddle;
import breakout.game.io.Keyboard;
import breakout.game.io.Mouse;
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

        Mouse mouse = state.getMouse();
        float direction = Math.signum(mouse.getX() - state.getWorld().getBounds().getWidth() / 2);

        if (keyboard.isKeyPressed(KeyEvent.VK_A) || (mouse.isPressed() && direction < 0)) {
            getBody().setVelocity(getBody().getVelocity().setX(-1 * 4f / dt));
        } else if (keyboard.isKeyPressed(KeyEvent.VK_D) || (mouse.isPressed() && direction > 0)) {
            getBody().setVelocity(getBody().getVelocity().setX(4f / dt));
        } else {
            getBody().setVelocity(getBody().getVelocity().setX(0));
        }

        getBody().move(dt);

        if (!isDestroyed()) {
            getHealth().regenerate(dt);
        }


    }

}
