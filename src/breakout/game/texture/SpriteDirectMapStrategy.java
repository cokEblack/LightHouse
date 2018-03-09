package breakout.game.texture;

import newton.geometry.Rectangle;

import java.util.TreeMap;

/**
 * A {@code SpriteDirectMapStrategy} is used when a sprite contains irregular
 * sub-images and to provide their mapping information.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class SpriteDirectMapStrategy implements SpriteMapStrategy<String> {

    /**
     * A map which holds all mapping information.
     *
     * This implementation uses a TreeMap to maintain the order of mappings
     * which is useful for animations.
     *
     */
    private TreeMap<String, Rectangle> mappings = new TreeMap<>();

    @Override
    public TreeMap<String, Rectangle> getMappings() {
        return mappings;
    }

    @Override
    public Rectangle getMapping(String name) {
        return mappings.get(name);
    }

    @Override
    public void setMapping(String name, int x, int y, int width, int height) {
        mappings.put(name, new Rectangle(x, y, width, height));
    }

}
