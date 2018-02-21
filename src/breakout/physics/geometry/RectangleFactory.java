package breakout.physics.geometry;

public class RectangleFactory implements ShapeFactory {

    @Override
    public Shape create(float x, float y, float width, float height) {
        return new Rectangle(x, y, width, height);
    }

}
