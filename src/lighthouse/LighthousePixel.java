package lighthouse;

import newton.geometry.Point;

import java.awt.*;

/**
 * This class represents a pixel on the lighthouse.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class LighthousePixel {

    /** The position of the pixel */
    private final Point position;

    /** The color of the pixel */
    private final Color fillColor;

    /**
     * Creates a pixel with a position and a color.
     *
     * @param position A position
     * @param fillColor A color
     */
    public LighthousePixel(Point position, Color fillColor) {
        this.position = position;
        this.fillColor = fillColor;
    }

    @Override
    public String toString() {
        return String.format(
                "%s {position=%s, fillColor=%s}",
                getClass().getName(),
                getPosition(),
                getFillColor()
        );
    }

    /**
     * Returns the position of the pixel.
     *
     * @return The position of the pixel
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns the position's x-coordinate of the pixel.
     *
     * @return The position's x-coordinate of the pixel
     */
    public int getX() {
        return Math.round(position.getX());
    }

    /**
     * Returns the position's x-coordinate of the pixel.
     *
     * @return The position's x-coordinate of the pixel
     */
    public int getY() {
        return Math.round(position.getY());
    }

    /**
     * Returns the color that the pixel is going to be filled with.
     *
     * @return The fill color
     */
    public Color getFillColor() {
        return fillColor;
    }

}
