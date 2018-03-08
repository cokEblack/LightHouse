package breakout.game.io;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class Keyboard extends KeyAdapter {

    /** A map which stores if a key is pressed or not */
    private final HashMap<Integer, Boolean> keys = new HashMap<>();

    @Override
    public String toString() {
        return keys.toString();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        keys.put(event.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent event) {
        keys.put(event.getKeyCode(), false);
    }

    public boolean isKeyPressed(int key) {
        return keys.getOrDefault(key, false);
    }

}
