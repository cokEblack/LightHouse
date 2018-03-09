package unitframeplugin.gui;

import breakout.game.Drawable;
import breakout.game.api.Buff;
import breakout.game.api.GameObject;
import breakout.game.gameobject.GameObjectResource;
import breakout.game.gameobject.GameplayAttribute;
import breakout.game.gameobject.Health;
import newton.geometry.Dimension;
import newton.geometry.Point;
import unitframeplugin.UnitFrameGameObjectProperty;
import unitframeplugin.UnitFramePlugin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.function.Supplier;

import static newton.util.Math.clamp;

/**
 * A unit frame is the visual representation of the a game object.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class UnitFrame implements Drawable {

    /** The width of the avatar image */
    public static final int AVATAR_WIDTH = 64;

    /** The height of the avatar image */
    public static final int AVATER_HEIGHT = 64;

    /** The width of a resource bar */
    public static final int RESOURCE_BAR_WIDTH = 192;

    /** The height of a resource bar */
    public static final int RESOURCE_BAR_HEIGHT = 32;

    /** The position of this unit frame */
    private Point position = new Point(0, 0);

    /** The dimension of this unit frame */
    private Dimension dimension = new Dimension(AVATAR_WIDTH + RESOURCE_BAR_WIDTH, AVATER_HEIGHT);

    /** The game object displayed by this unit frame */
    private GameObject unit;

    /**
     * Sets the game object displayed by this unit frame.
     *
     * @param unit A game object
     */
    public void setUnit(GameObject unit) {

        this.unit = unit;

        // Generate a placeholder avatar if the provided game object has not avatar image
        if (unit.getData(UnitFrameGameObjectProperty.UNIT_FRAME_AVATAR_IMAGE) == null) {
            unit.setData(UnitFrameGameObjectProperty.UNIT_FRAME_AVATAR_IMAGE, createPlaceholderAvatar());
        }

    }

    /**
     * Creates a placerholder avatar image.
     *
     * @return A placeholder avatar image
     */
    private BufferedImage createPlaceholderAvatar() {

        // TODO this could be a static resource that is only created once
        BufferedImage image = new BufferedImage(AVATAR_WIDTH, AVATER_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics g = image.getGraphics();
        g.setColor(new Color(0xdddddd));
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();

        return image;

    }

    /**
     * Returns the game object that is currently displayed by this unit frame.
     *
     * @return A game object
     */
    public GameObject getUnit() {
        return unit;
    }

    /**
     * The position of this unit frame.
     *
     * @return The position of this unit frame
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Sets the position of this unit frame.
     *
     * @param position A position
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * Returns the dimension of this unit frame.
     *
     * @return The dimension of this unit frame
     */
    public Dimension getDimension() {
        return dimension;
    }
    
    @Override
    public void draw(Graphics g) {

        int x = (int) getPosition().getX();
        int y = (int) getPosition().getY();

        // Draw the avatar image
        BufferedImage avatar = (BufferedImage) getUnit().getData(UnitFrameGameObjectProperty.UNIT_FRAME_AVATAR_IMAGE);
        g.drawImage(avatar, x, y, null);

        // Draw background bar and health bar
        g.setColor(new Color(0xdddddd));
        g.fillRect(x + AVATAR_WIDTH, y, RESOURCE_BAR_WIDTH, RESOURCE_BAR_HEIGHT);
        g.setColor(new Color(0x32cd32));
        g.fillRect(x + AVATAR_WIDTH, y, Math.round(RESOURCE_BAR_WIDTH * calcResourceInPercent(getUnit().getHealth())), RESOURCE_BAR_HEIGHT);

        // Draw the string representation of the health object
        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 24));

        Health health = getUnit().getHealth();

        // Determine the health status string
        String healthStatus;
        if (getUnit().isDestroyed()) {
            healthStatus = "Dead";
        } else {
            healthStatus = String.format("%.0f/%.0f", health.getCurrentValue(), health.getMaximumValue());
        }

        // Set string color
        g.setColor(new Color(0x222222));

        // Draw the string in the center of the health bar
        FontMetrics fm = g.getFontMetrics();
        Rectangle2D stringBounds = fm.getStringBounds(healthStatus, g);
        g.drawString(
            healthStatus,
            (int) (x + AVATAR_WIDTH + (RESOURCE_BAR_WIDTH - stringBounds.getWidth()) / 2),
            (int) (y + (RESOURCE_BAR_HEIGHT - stringBounds.getHeight()) / 2) + fm.getAscent()
        );

        // Draw the secondary resource if available
        if (getSecondaryResource() != null) {

            GameObjectResource resource = getSecondaryResource();

            // Draw Background of secondary resource
            g.setColor(new Color(0xdddddd));
            g.fillRect(
                    x + AVATAR_WIDTH,
                    y + RESOURCE_BAR_HEIGHT,
                    RESOURCE_BAR_WIDTH,
                    RESOURCE_BAR_HEIGHT
            );

            // Draw actual resource bar of secondary resource
            g.setColor(new Color(0x4169e1));
            g.fillRect(
                    x + AVATAR_WIDTH,
                    y + RESOURCE_BAR_HEIGHT,
                    Math.round(RESOURCE_BAR_WIDTH * calcResourceInPercent(resource)),
                    RESOURCE_BAR_HEIGHT
            );

        }

    }

    /**
     * Returns the set secondary resource of the current game object.
     *
     * @return A resource
     */
    private GameObjectResource getSecondaryResource() {
        return (GameObjectResource) getUnit().getData(UnitFrameGameObjectProperty.UNIT_FRAME_SECONDARY_RESOURCE);
    }

    /**
     * Calculate the percentage of the resource's current value in relation to
     * the maximum value.
     *
     * @param resource A resource
     * @return A percentage
     */
    private float calcResourceInPercent(GameObjectResource resource) {
        return clamp(0, 1, (resource.getCurrentValue() / resource.getMaximumValue()));
    }

}
