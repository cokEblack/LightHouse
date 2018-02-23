package breakout.game.api;

import breakout.game.GameObjectBody;
import breakout.game.Health;
import breakout.game.texture.Texture;
import breakout.physics.Body;
import breakout.physics.geometry.Shape;

import java.awt.Graphics;

public abstract class AbstractGameObject implements GameObject {

    private String name;
    private GameObjectBody body;
    private Health health;
    private Texture texture;

    @Override
    public GameObjectBody getBody() {
        return body;
    }

    @Override
    public void setBody(GameObjectBody body) {
        this.body = body;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public void setHealth(Health health) {
        this.health = health;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                getTexture().getImage(),
                (int) getBody().getX(),
                (int) getBody().getY(),
                (int) getBody().getWidth(),
                (int) getBody().getHeight(),
                null
        );
    }

}
