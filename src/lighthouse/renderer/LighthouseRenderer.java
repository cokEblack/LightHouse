package lighthouse.renderer;

import breakout.game.api.GameObject;
import breakout.game.renderer.Renderer;
import breakout.game.state.GameState;
// import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import lighthouse.GameObjectTransformer;
import lighthouse.LighthousePixel;

import java.io.IOException;

public class LighthouseRenderer implements Renderer {

    private static final int WIDTH = 28;
    private static final int HEIGHT = 14;
    private static final int NUMBER_OF_COLOR_CHANNELS = 3;

    private static final int RED_INDEX = 0;
    private static final int GREEN_INDEX = 1;
    private static final int BLUE_INDEX = 2;

    // private final LighthouseDisplay display;

    {

        /*
        display = new LighthouseDisplay("", "");

        try {
            display.connect();
        } catch (Exception exception) {
            System.out.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }
        */

    }

    @Override
    public void render(GameState state) {

        GameObjectTransformer transformer = new GameObjectTransformer();

        byte[] buffer = new byte[WIDTH * HEIGHT * NUMBER_OF_COLOR_CHANNELS];

        for (GameObject gameObject : state.getGameObjects()) {

            LighthousePixel pixel = transformer.transform(gameObject);

            int index = pixel.getY() / 100 * WIDTH + pixel.getX() / 100;
            buffer[index + RED_INDEX] = (byte) pixel.getFillColor().getRed();
            buffer[index + GREEN_INDEX] = (byte) pixel.getFillColor().getGreen();
            buffer[index + BLUE_INDEX] = (byte) pixel.getFillColor().getBlue();

        }

        /*
        try {
            display.send(buffer);
        } catch (IOException exception) {
            System.err.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }
        */

    }

}
