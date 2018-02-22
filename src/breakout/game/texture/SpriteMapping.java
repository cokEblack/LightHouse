package breakout.game.texture;

import breakout.physics.geometry.Rectangle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SpriteMapping {

    private Rectangle bounds;

    public SpriteMapping() {
        this(new Rectangle(0, 0, 0, 0));
    }

    @Override
    public String toString() {
        return bounds.toString();
    }

    public SpriteMapping(Rectangle bounds) {
        this.bounds = bounds;
    }

    public float getX() {
        return bounds.getX();
    }

    @XmlAttribute(name="x", required=true)
    public void setX(float x) {
        bounds.setX(x);
    }

    public float getY() {
        return bounds.getX();
    }

    @XmlAttribute(name="y", required=true)
    public void setY(float y) {
        bounds.setY(y);
    }

    public float getWidth() {
        return bounds.getWidth();
    }

    @XmlAttribute(name="width", required=true)
    public void setWidth(float width) {
        bounds.setWidth(width);
    }

    public float getHeight() {
        return bounds.getHeight();
    }

    @XmlAttribute(name="height", required=true)
    public void setHeight(float height) {
        bounds.setHeight(height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
