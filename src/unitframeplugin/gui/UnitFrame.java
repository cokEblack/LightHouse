package unitframeplugin.gui;

import breakout.game.Drawable;
import breakout.game.api.GameObject;
import breakout.game.gameobject.Health;
import newton.geometry.Dimension;
import newton.geometry.Point;
import unitframeplugin.UnitFrameGameObjectProperty;
import unitframeplugin.UnitFramePlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static newton.util.Math.clamp;

public class UnitFrame implements Drawable {

    private Point position = new Point(0, 0);
    private Dimension dimension = new Dimension(264, 64);
    private int healthBarWidth = (int) dimension.getWidth();

    private BufferedImage defaultAvatar;

    {
        try {
            defaultAvatar = ImageIO.read(UnitFramePlugin.class.getResource("resources/avatar.png"));
        } catch (IOException exception) {}
    }

    private GameObject unit;

    public void setUnit(GameObject unit) {
        this.unit = unit;
    }

    public GameObject getUnit() {
        return unit;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public void draw(Graphics g) {

        int x = (int) getPosition().getX();
        int y = (int) getPosition().getY();
        int width = (int) getDimension().getWidth();
        int height = (int) getDimension().getHeight();

        BufferedImage avatar = (BufferedImage) getUnit().getData(UnitFrameGameObjectProperty.UNIT_FRAME_AVATAR_IMAGE);
        if (avatar == null) {
            /*
            avatar = new BufferedImage(64, 64, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics avatarGraphics = avatar.getGraphics();
            avatarGraphics.setColor(new Color(0xdddddd));
            avatarGraphics.fillRect(0, 0, 64, 64);
            */
            avatar = defaultAvatar;
        }

        g.drawImage(avatar, x, y, null);

        // Draw health bar
        g.setColor(new Color(0xdddddd));
        g.fillRect(x + 64, y, healthBarWidth, height);
        g.setColor(new Color(0x32cd32));
        g.fillRect(x + 64, y, Math.round(healthBarWidth * calcHealthInPercent()), height);

    }

    private float calcHealthInPercent() {
        Health health = getUnit().getHealth();
        return clamp(0, 1, (health.getCurrentValue() / health.getMaximumValue()));
    }

}
