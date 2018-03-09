package unitframeplugin;

import breakout.game.Game;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.api.Plugin;
import newton.geometry.Point;
import unitframeplugin.gui.UnitFrame;

import java.awt.*;
import javax.swing.JPanel;

/**
 * The unit frame plugins adds a view to the window which provides the a visual
 * representation of a game object and its attributes.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class UnitFramePlugin implements Plugin {

    /** The main container of this plugin */
    private JPanel unitFramePanel;

    /** The unit frame */
    private UnitFrame unitFrame;

    @Override
    public void register(Game game) {

        unitFrame = new UnitFrame();

        // The unit frame panel renders the unit frame
        unitFramePanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                unitFrame.draw(g);
            }

        };

        unitFramePanel.setBackground(new Color(0x222222));

        // Set the dimensions of the panel to cover the lower section of the window
        float worldWidth = game.getGameState().getWorld().getWidth();
        float unitFrameHeight = 200;

        unitFramePanel.setMinimumSize(new Dimension((int) worldWidth, (int) unitFrameHeight));
        unitFramePanel.setPreferredSize(new Dimension((int) worldWidth, (int) unitFrameHeight));

        // Center the unit frame
        unitFrame.setPosition(new Point(
        (worldWidth - unitFrame.getDimension().getWidth()) / 2,
            (unitFrameHeight - unitFrame.getDimension().getHeight()) / 2
        ));

        // Add the unit frame panel
        game.getWindow().add(unitFramePanel, BorderLayout.PAGE_END);
        game.getWindow().pack();

        // Render the unit frame inside the game loop
        game.addGameListener(new GameListener() {

            @Override
            public void onRender(GameLoopEvent event) {
                unitFramePanel.repaint();
            }

        });

    }

    @Override
    public void unregister(Game game) {
        // TODO remove game listener that renders the unit frame
        game.getWindow().remove(unitFramePanel);
    }

    /**
     * Returns the unit frame.
     *
     * @return The unit frame
     */
    public UnitFrame getUnitFrame() {
        return unitFrame;
    }

}
