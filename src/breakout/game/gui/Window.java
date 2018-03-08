package breakout.game.gui;

import breakout.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final Game game;
    private final JPanel canvas;

    {

        setTitle("Breakout");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        setResizable(false);
        setLocationRelativeTo(null);

    }

    public Window(Game game) {

        this.game = game;

        this.canvas = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                game.render(g);
            }

        };

        this.canvas.setMinimumSize(new Dimension(32 * 28, 32 * 14));
        this.canvas.setPreferredSize(new Dimension(32 * 28, 32 * 14));

        add(canvas, BorderLayout.CENTER);

    }

}
