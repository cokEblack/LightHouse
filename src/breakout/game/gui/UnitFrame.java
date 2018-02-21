package breakout.game.gui;

import breakout.game.Drawable;
import breakout.game.api.GameObject;

import java.awt.*;

public class UnitFrame implements Drawable {

    private GameObject gameObject;

    public UnitFrame(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void draw(Graphics g) {

        // TODO draw health bar
        // TODO draw secondary resource bar
        // TODO draw name
        // TODO draw portrait
        // TODO draw active buffs

    }

}
