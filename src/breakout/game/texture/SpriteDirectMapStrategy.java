package breakout.game.texture;

import breakout.physics.geometry.Rectangle;

import java.util.TreeMap;

public class SpriteDirectMapStrategy implements SpriteMapStrategy<String> {

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
