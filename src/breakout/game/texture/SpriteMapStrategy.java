package breakout.game.texture;

// TODO it is weird to depend on a class of the physics engine
import breakout.physics.geometry.Rectangle;

import java.util.Map;

// TODO consider making this interface a generic interface to allow different keys (not only strings)

/**
 *
 *
 * @author Melf Kammholz
 *
 * @param <K> Type of the keys used to refer to a mapping
 */
public interface SpriteMapStrategy <K> {

    Rectangle getMapping(K name);
    Map<K, Rectangle> getMappings();
    void setMapping(K name, int x, int y, int width, int height);
    // TODO provide an iterator?

}
