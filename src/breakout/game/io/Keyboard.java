package breakout.game.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Keyboard extends KeyAdapter {

    private final HashMap<Integer, Boolean> keys;

    {
        keys = new HashMap<>();
    }

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
