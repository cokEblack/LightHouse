package newton.physics;

import newton.geometry.Line;
import newton.geometry.Point;

public class Border extends Body {

    public Border(World world, Point from, Point to) {
        super(Line.createFromTwoPoints(from, to), 0, world);
    }

}
