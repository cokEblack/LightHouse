package lighthouse;

import breakout.game.api.GameObject;
import newton.geometry.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The {@code GameObjectTransformer} uses a game object to create a
 * {@code LighthousePixel} by extracting the information needed to display
 * a representation of the game object on the lighthouse display.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GameObjectTransformer {

    /**
     * Transform a game object by extracting the information needed to display
     * a representation of the game object on the lighthouse display.
     *
     * @param gameObject A game object
     * @return A lighthouse pixel
     */
    public LighthousePixel transform(GameObject gameObject) {

        Point position = gameObject.getBody().getPosition();
        Color fillColor = (Color) gameObject.getData(LighthouseGameObjectProperty.LIGHTHOUSE_FILL_COLOR);

        return new LighthousePixel(position.setX((int) position.getX() / 32).setY((int) position.getY() / 32), fillColor);

    }

    /**
     * Calculates the average color of an image.
     *
     * This method ignores pixels that are not fully opaque.
     *
     * @param image An image
     * @return The average color of the image
     */
    public static Color calcAverageColor(BufferedImage image) {

        // Masks used to extract the color channel value
        final int ALPHA_MASK = 0xff000000;
        final int RED_MASK = 0x00ff0000;
        final int GREEN_MASK = 0x0000ff00;
        final int BLUE_MASK = 0x000000ff;

        int r = 0, g = 0, b = 0;
        int n = 0;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {

                int color = image.getRGB(x, y);

                int alpha = (color & ALPHA_MASK) >>> 24;

                // Ignore any color that is not fully opaque
                if (alpha != 0xff) continue;

                r += (color & RED_MASK) >>> 16;
                g += (color & GREEN_MASK) >>> 8;
                b += (color & BLUE_MASK);

                n++;

            }
        }

        return new Color(r / n, g / n, b / n);

    }

}
