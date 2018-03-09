package breakout.game.texture;

import newton.geometry.Rectangle;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class is mainly used to decode the information provided by XML files
 * which contains sprite mappings.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class SpriteMapping {

    /**
     * The bounds of an sub-image
     */
    private Rectangle bounds;

    /**
     * Creates a sprite mapping at the position (0, 0) and the
     * dimension set to (0, 0)
     */
    public SpriteMapping() {
        this(new Rectangle(0, 0, 0, 0));
    }

    /**
     * Creates a sprite mapping by using a rectangle.
     *
     * @param bounds A rectangle
     */
    public SpriteMapping(Rectangle bounds) {
        this.bounds = bounds;
    }

    @Override
    public String toString() {
        return bounds.toString();
    }

    /**
     * Returns the x-coordinate of the position.
     *
     * @return The x-coordinate of the position
     */
    public float getX() {
        return bounds.getX();
    }

    /**
     * Sets the x-coordinate of the position.
     *
     * @param x A x-coordinate
     */
    @XmlAttribute(name="x", required=true)
    public void setX(float x) {
        bounds.setX(x);
    }

    /**
     * Returns the y-coordinate of the position.
     *
     * @return The y-coordinate of the position
     */
    public float getY() {
        return bounds.getX();
    }

    /**
     * Sets the y-coordinate of the position.
     *
     * @param y A y-coordinate
     */
    @XmlAttribute(name="y", required=true)
    public void setY(float y) {
        bounds.setY(y);
    }

    /**
     * Returns the width of the sub-image
     *
     * @return The width of the sub-image
     */
    public float getWidth() {
        return bounds.getWidth();
    }

    /**
     * Sets the width of the sub-image.
     *
     * @param width The width of the sub-image.
     */
    @XmlAttribute(name="width", required=true)
    public void setWidth(float width) {
        bounds.setWidth(width);
    }

    /**
     * Returns the height of the sub-image
     *
     * @return The height of the sub-image
     */
    public float getHeight() {
        return bounds.getHeight();
    }

    /**
     * Sets the height of the sub-image.
     *
     * @param height The width of the sub-image.
     */
    @XmlAttribute(name="height", required=true)
    public void setHeight(float height) {
        bounds.setHeight(height);
    }

    /**
     * Returns the bounds of the sub-image.
     *
     * @return The bounds of the sub-image
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Sets the bounds of the sub-image.
     *
     * @param bounds The bounds of the sub-image.
     */
    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}
