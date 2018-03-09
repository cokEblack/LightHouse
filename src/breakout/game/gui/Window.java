package breakout.game.gui;

import breakout.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of a game.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class Window extends JFrame {

    /** Reference of the game */
    private final Game game;

    /** The canvas that displays the game state */
    private final JPanel canvas;

    {

        // Default configuration of the JFrame
        setTitle("Breakout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setResizable(false);
        setLocationRelativeTo(null);

    }

    /**
     * Creates a window with a reference to the game that it displays.
     *
     * @param game A game
     */
    public Window(Game game) {

        this.game = game;

        // The canvas renders the game state
        this.canvas = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                game.render(g);
            }

        };

        // Sorry for the hardcoded values :(
        this.canvas.setMinimumSize(new Dimension(32 * 28, 32 * 14));
        this.canvas.setPreferredSize(new Dimension(32 * 28, 32 * 14));

        add(canvas, BorderLayout.CENTER);

    }

}
