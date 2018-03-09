package breakout.game.io;

import newton.geometry.Point;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * This class tracks the position of the mouse.
 *
 * @author Melf Kammholz
 */
public class Mouse implements MouseListener, MouseMotionListener {

    /** The current position of this mouse */
    private Point position = new Point(0, 0);

    /** Is any of the mouse buttons pressed? */
    private boolean isPressed = false;

    @Override
    public String toString() {
        return String.format(
            "%s {position=%s, isPressed=%s}",
            getClass().getName(),
            position,
            isPressed ? "true" : "false"
        );
    }

    /**
     * The x-coordinate of the mouse's position.
     *
     * @return The x-coordinate of the mouse's position.
     */
    public float getX() {
        return position.getX();
    }

    /**
     * The y-coordinate of the mouse's position.
     *
     * @return The y-coordinate of the mouse's position.
     */
    public float getY() {
        return position.getY();
    }

    public Point getPosition() {
        return position;
    }

    /**
     *
     * @return Returns {@code true}, if any of the mouse buttons is pressed
     */
    public boolean isPressed() {
        return isPressed;
    }

    @Override
    public void mouseClicked(MouseEvent event) {}

    @Override
    public void mousePressed(MouseEvent event) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent event) {
        isPressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent event) {}

    @Override
    public void mouseExited(MouseEvent event) {}

    @Override
    public void mouseDragged(MouseEvent event) {
        position = createPointFromMouseEvent(event);
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        position = createPointFromMouseEvent(event);
    }

    /**
     * Creates a point from a mouse event.
     *
     * @param event A mouse event
     * @return Returns the position of the mouse
     */
    private Point createPointFromMouseEvent(MouseEvent event) {
        return new Point(event.getX(), event.getY());
    }

}
