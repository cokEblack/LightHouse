package breakout.game.api;

public interface BrickFactory <T extends Brick> {
    T create();
}
