package breakout.physics;


import java.util.ArrayList;
import java.util.List;

public class World {

    private List<Body> bodies = new ArrayList<>();

    public World() {}

    public List<Body> getBodies() {
        return bodies;
    }

}
