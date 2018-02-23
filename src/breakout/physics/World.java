package breakout.physics;

import breakout.physics.geometry.Rectangle;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code World} serves a frame of reference for a set of bodies
 * which are affected by boundaries of this world.
 *
 * @author Melf Kammholz
 */
public class World {

    private Rectangle bounds;
    private Set<Body> bodies = new HashSet<>();
    private Gravity gravity;

    /**
     * Creates a world with the provided dimension and gravity.
     *
     * @param width Width of the world
     * @param height Height of the world
     * @param gravity Gravity which affects all bodies in this world
     */
    public World(float width, float height, Gravity gravity) {
        bounds = new Rectangle(0, 0, width, height);
        this.gravity = gravity;
    }

    public void addBody(Body body) {
        body.setWorld(this);
        bodies.add(body);
    }

    public void removeBody(Body body) {
        body.setWorld(null);
        removeBody(body);
    }

    public Set<Body> getBodies() {
        return bodies;
    }

}
