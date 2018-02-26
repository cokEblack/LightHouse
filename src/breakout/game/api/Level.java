package breakout.game.api;

/**
 * A level is practically a plugin for the game. It provides all the
 * necessary information a start the level.
 *
 * @author Melf Kammholz
 */
public interface Level {

    /**
     * Creates a ball factory from which the game can create balls.
     *
     * @return A ball factory
     */
    GameObjectFactory<Ball> createBallFactory();

    /**
     * Creates a brick factory from which the game can create balls.
     *
     * @return A brick factory
     */
    GameObjectFactory<Brick> createBrickFactory();

    /**
     * Creates a paddle factory from which the game can create balls.
     *
     * @return A paddle factory
     */
    GameObjectFactory<Paddle> createPaddleFactory();

}
