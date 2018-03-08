package unitframeplugin;

import breakout.game.Game;
import breakout.game.GameListener;
import breakout.game.GameLoopEvent;
import breakout.game.api.Plugin;
import newton.geometry.Point;
import unitframeplugin.gui.UnitFrame;

import java.awt.*;
import javax.swing.JPanel;

public class UnitFramePlugin implements Plugin {

    private JPanel unitFramePanel;
    private UnitFrame unitFrame;

    @Override
    public void register(Game game) {

        unitFrame = new UnitFrame();

        unitFramePanel = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            unitFrame.draw(g);
            }

        };

        unitFramePanel.setBackground(new Color(0x222222));

        float worldWidth = game.getGameState().getWorld().getWidth();
        float unitFrameHeight = 200;

        unitFramePanel.setMinimumSize(new Dimension((int) worldWidth, (int) unitFrameHeight));
        unitFramePanel.setPreferredSize(new Dimension((int) worldWidth, (int) unitFrameHeight));

        unitFrame.setPosition(new Point(
        (worldWidth - unitFrame.getDimension().getWidth()) / 2,
            (unitFrameHeight - unitFrame.getDimension().getHeight()) / 2
        ));

        game.getWindow().add(unitFramePanel, BorderLayout.PAGE_END);
        game.getWindow().pack();

        game.addGameListener(new GameListener() {

            @Override
            public void onRender(GameLoopEvent event) {
                unitFramePanel.repaint();
            }

        });

    }

    @Override
    public void unregister(Game game) {
        game.getWindow().remove(unitFramePanel);
    }

    public UnitFrame getUnitFrame() {
        return unitFrame;
    }

}
