package breakout;

import breakout.game.AbstractGame;
import breakout.game.api.*;

/**
 * The main class of this program.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Breakout {

    /**
     * Starts the game.
     *
     * @param args A set of arguments passed to this program
     */
    public static void main(String[] args) {

        // The level loading implementation was originally meant to be used to
        // dynamically load levels via the CLI or a graphical user interface as
        // external jar files, but it had low priority
        Level level = null;
        Class<? extends Level> levelClass = null;

        try {
            levelClass = (Class<? extends Level>) Class.forName("area52.GreatestLevel");
        } catch (ClassNotFoundException exception) {
            throw new RuntimeException("The level cannot be found.");
        }

        try {
            level = levelClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            throw new RuntimeException("Each level must implement a default constructor with no formal parameters.");
        }

        // Create a game and set the level
        AbstractGame game = new AbstractGame() {};
        game.setLevel(level);

        // Start the game
        Thread gameThread = new Thread(game);
        gameThread.start();


    }

}

