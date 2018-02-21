package breakout.physics.geometry;

public interface Shape {

    float getX();
    void setX(float x);
    float getY();
    void setY(float y);
    Vector getPosition();
    void setPosition(float x, float y);
    void setPosition(Point point);
    float getWidth();
    void setWidth(float width);
    float getHeight();
    void setHeight(float height);
    Dimension getDimension();
    void setDimension(float width, float height);
    void setDimension(Dimension dimension);
    boolean intersects(Shape shape);

}
