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

        // As most arcade games were played on CRT TVs, the aspect ratio of
        // this game must be 4:3 to create the retro vibes
        // TODO Lighthouse aspect ratio 2:1 (28x14)
        setSize(640, (640 / 4) * 3);
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

        add(canvas);

    }

}
