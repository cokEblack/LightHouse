package breakout.game;

import java.awt.Graphics;

/**
 * The {@code Drawable} interface is used while rendering
 * by each implementing class to a graphics context.
 *
 * @author Melf Kammholz
 */
@FunctionalInterface
public interface Drawable {

    /**
     * Draws what is specified in the implemented method.
     *
     * @param g A graphics context
     */
    void draw(Graphics g);

}
