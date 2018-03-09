package lighthouse.renderer;

import breakout.game.api.GameObject;
import breakout.game.renderer.Renderer;
import breakout.game.state.GameState;
import de.cau.infprogoo.lighthouse.LighthouseDisplay;
import lighthouse.GameObjectTransformer;
import lighthouse.LighthousePixel;
import newton.geometry.Rectangle;

import java.io.IOException;

/**
 * The {@code LighthouseRenderer} renders the current game state to the
 * lighthouse.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class LighthouseRenderer implements Renderer {

    /** The width of the lighthouse display */
    private static final int WIDTH = 28;

    /** The height of the lighthouse display */
    private static final int HEIGHT = 14;

    /** The number of color channels */
    private static final int NUMBER_OF_COLOR_CHANNELS = 3;

    /** The sub-index of the red channel inside the byte buffer */
    private static final int RED_INDEX = 0;

    /** The sub-index of the green channel inside the byte buffer */
    private static final int GREEN_INDEX = 1;

    /** The sub-index of the blue channel inside the byte buffer */
    private static final int BLUE_INDEX = 2;

    /** A reference to the lighthouse display */
    private final LighthouseDisplay display;

    {

        // Setup the connection to the lighthouse display
        display = new LighthouseDisplay("stu209214", "API-TOK_gpzz-EnW4-7KAC-hft0-993n");

        try {
            display.connect();
        } catch (Exception exception) {
            System.out.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }

    }

    /**
     * Renders the current game state to the lighthouse display.
     *
     * The method creates an byte buffer which holds a visual representation
     * of the game state. Each pixel is represented by an rectangle which covers
     * a certain area of the world's dimension. Each game object that interferes
     * with this pixel is display by the pixel. The last interference determines
     * the color of the pixel.
     *
     * @param state A game state
     */
    @Override
    public void render(GameState state) {

        GameObjectTransformer transformer = new GameObjectTransformer();

        byte[] buffer = new byte[WIDTH * HEIGHT * NUMBER_OF_COLOR_CHANNELS];

        // Calculate the resolution
        float resX = state.getWorld().getWidth() / WIDTH;
        float resY = state.getWorld().getHeight() / HEIGHT;

        for (int i = 0, n = buffer.length / NUMBER_OF_COLOR_CHANNELS; i < n; i++) {

            float x = (i % WIDTH) * resX;
            float y = (i / WIDTH) * resY;

            Rectangle pixelRect = new Rectangle(x, y, resX, resY);

            // Check which game objects interfere with the pixel
            for (GameObject gameObject : state.getGameObjects()) {

                if (gameObject.isDestroyed()) {
                    continue;
                }

                Rectangle gameObjectBounds = gameObject.getBody().getBounds();

                if (!pixelRect.intersects(gameObjectBounds)) {
                    continue;
                }

                // Transform the game object
                LighthousePixel pixel = transformer.transform(gameObject);

                // ... and set the representation inside the buffer
                int index = NUMBER_OF_COLOR_CHANNELS * i;
                buffer[index + RED_INDEX] = (byte) pixel.getFillColor().getRed();
                buffer[index + GREEN_INDEX] = (byte) pixel.getFillColor().getGreen();
                buffer[index + BLUE_INDEX] = (byte) pixel.getFillColor().getBlue();

            }

        }

        // Send the byte buffer
        try {
            display.send(buffer);
        } catch (IOException exception) {
            System.err.println("Connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }

    }

}
