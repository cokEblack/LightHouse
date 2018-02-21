package breakout.physics;

import breakout.physics.geometry.Vector;

public class Gravity {

    private final Vector acceleration;

    public Gravity(float dx, float dy) {
        acceleration = new Vector(dx, dy);
    }

    public Gravity(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public Vector getAcceleration() {
        return acceleration;
    }

    public float getX() {
        return acceleration.getX();
    }

    public float getY() {
        return acceleration.getY();
    }

}
