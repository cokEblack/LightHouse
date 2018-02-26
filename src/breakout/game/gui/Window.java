package breakout.game.gui;

import breakout.game.Game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final Game game;
    private final JPanel canvas;

    {

        setTitle("Breakout"); // TODO consider setting this while the object is constructed
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // As most arcade games were played on CRT TVs, the aspect ratio of
        // this game must be 4:3 to create the retro vibes
        // TODO Lighthouse aspect ratio 2:1 (28x14)

        // TODO deal with insets
        pack();
        Insets insets = getInsets();
        // setSize(insets.left + insets.right + 640, insets.top + insets.bottom + (640 / 4) * 3);
        setSize(insets.left + 640, insets.top + (640 / 4) * 3);

        setResizable(false);
        setLocationRelativeTo(null);

    }

    public Window(Game game) {

        this.game = game;

        {
            setPreferredSize(new Dimension(640, 480));
        }

        this.canvas = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                game.render(g);
            }

        };

        add(canvas, BorderLayout.CENTER);

    }

}
