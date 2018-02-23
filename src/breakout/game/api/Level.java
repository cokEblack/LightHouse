package breakout.game.api;

import java.util.List;

/**
 * A level is practically a plugin for the game. It provides all the
 * necessary information a start the level.
 *
 * @author Melf Kammholz
 */
public interface Level {

    List<BrickFactory<? extends Brick>> getBricks();
    Ball getBall();

}
