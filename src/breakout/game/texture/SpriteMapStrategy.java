package breakout.game.texture;

import newton.geometry.Rectangle;

import java.util.Map;

/**
 * A sprite mapping strategy is used to provide an interface to query a sprite
 * image.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 * @param <K> Type of the keys used to refer to a mapping
 */
public interface SpriteMapStrategy <K> {

    /**
     * Returns the bounds of a sub-image of a sprite.
     *
     * @param name The name of the mapping
     * @return The bounds of the sub-image
     */
    Rectangle getMapping(K name);

    /**
     * Returns the map that holds all mapping information.
     *
     * @return The map that holds all mapping information.
     */
    Map<K, Rectangle> getMappings();

    /**
     * Sets the bounds of a sub-image of a sprite.
     *
     * @param name Name of the mapping
     * @param x A x-coordinate
     * @param y A y-coordinate
     * @param width A width
     * @param height A height
     */
    void setMapping(K name, int x, int y, int width, int height);

}
