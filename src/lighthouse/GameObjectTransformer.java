package lighthouse;

import breakout.game.api.GameObject;
import newton.geometry.Point;

import java.awt.*;

public class GameObjectTransformer {

    public LighthousePixel transform(GameObject gameObject) {

        Point position = gameObject.getBody().getPosition();

        // Get a color based on the texture
        // Color color = gameObject.getTexture()
        Color fillColor = new Color(255, 255, 255);

        return new LighthousePixel(position, fillColor);

    }

}
