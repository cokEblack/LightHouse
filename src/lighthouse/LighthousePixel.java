package lighthouse;

import newton.geometry.Point;

import java.awt.*;

public class LighthousePixel {

    private final Point position;
    private final Color fillColor;

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

    public Point getPosition() {
        return position;
    }

    public int getX() {
        return Math.round(position.getX());
    }

    public int getY() {
        return Math.round(position.getY());
    }

    public Color getFillColor() {
        return fillColor;
    }

}
