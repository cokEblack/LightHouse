package breakout.game.api;

import breakout.game.Drawable;
import breakout.game.GameObjectBody;
import breakout.game.Health;
import breakout.game.state.GameState;
import breakout.game.texture.Texture;
import breakout.physics.Body;
import breakout.physics.geometry.Shape;

/**
 * Each game object has to consist out of the following:
 * - a physical body
 * - a texture
 * - health? GameObjectResource
 */
public interface GameObject extends Drawable {

    GameObjectBody getBody();
    void setBody(GameObjectBody body);
    String getName();
    void setName(String name);
    Texture getTexture();
    Health getHealth();
    void setHealth(Health health);
    void update(int dt, GameState state);

}