package breakout.game.gameobject;

public interface GameObjectListener {

    default public void onDestroy(GameObjectEvent event) {}

}
