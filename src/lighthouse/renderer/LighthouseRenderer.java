package lighthouse.renderer;

import breakout.game.api.Ball;
import breakout.game.api.GameObject;
import breakout.game.renderer.Renderer;
import breakout.game.state.GameState;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import lighthouse.GameObjectTransformer;
import lighthouse.LighthousePixel;
import newton.geometry.Rectangle;

import java.io.IOException;

public class LighthouseRenderer implements Renderer {

    private static final int WIDTH = 28;
    private static final int HEIGHT = 14;
    private static final int NUMBER_OF_COLOR_CHANNELS = 3;

    private static final int RED_INDEX = 0;
    private static final int GREEN_INDEX = 1;
    private static final int BLUE_INDEX = 2;

    private final LighthouseDisplay display;

    {

        display = new LighthouseDisplay("stu209214", "API-TOK_gpzz-EnW4-7KAC-hft0-993n");

        try {
            display.connect();
        } catch (Exception exception) {
            System.out.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }

    }

    public LighthouseRenderer() {
    }

    @Override
    public void render(GameState state) {

        GameObjectTransformer transformer = new GameObjectTransformer();

        byte[] buffer = new byte[WIDTH * HEIGHT * NUMBER_OF_COLOR_CHANNELS];

        float resX = state.getWorld().getWidth() / WIDTH;
        float resY = state.getWorld().getHeight() / HEIGHT;

        for (int i = 0, n = buffer.length / NUMBER_OF_COLOR_CHANNELS; i < n; i++) {

            float x = (i % WIDTH) * resX;
            float y = (i / WIDTH) * resY;

            Rectangle pixelRect = new Rectangle(x, y, resX, resY);

            for (GameObject gameObject : state.getGameObjects()) {

                if (gameObject.isDestroyed()) {
                    continue;
                }

                Rectangle gameObjectBounds = gameObject.getBody().getBounds();

                if (!pixelRect.intersects(gameObjectBounds)) {
                    continue;
                }

                LighthousePixel pixel = transformer.transform(gameObject);

                int index = NUMBER_OF_COLOR_CHANNELS * i;
                buffer[index + RED_INDEX] = (byte) pixel.getFillColor().getRed();
                buffer[index + GREEN_INDEX] = (byte) pixel.getFillColor().getGreen();
                buffer[index + BLUE_INDEX] = (byte) pixel.getFillColor().getBlue();

            }

        }

        try {
            if (true) {
                display.send(buffer);
            }
        } catch (IOException exception) {
            System.err.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }

    }

}
