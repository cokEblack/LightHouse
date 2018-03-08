package breakout;

import breakout.game.AbstractGame;
import breakout.game.api.*;

public class Breakout {

    public static void main(String[] args) {

        Level level = null;
        Class<? extends Level> levelClass = null;

        try {
            // TODO This class should either be given by the arguments provided to this program or a graphical interface.
            // ... if this is fully implemented, this program is solely a player for levels.
            levelClass = (Class<? extends Level>) Class.forName("area52.GreatestLevel");
        } catch (ClassNotFoundException exception) {
            System.err.println("The level cannot be found.");
        }

        try {
            level = levelClass.getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException exception) {
            System.err.println("Each level must implement a default constructor with no formal parameters.");
            exception.printStackTrace();
        }

        AbstractGame game = new AbstractGame() {};
        game.setLevel(level);

        Thread gameThread = new Thread(game);
        gameThread.start();


    }

}

