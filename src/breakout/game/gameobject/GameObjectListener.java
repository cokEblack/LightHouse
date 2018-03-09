package breakout.game.gameobject;

/**
 * A {@code GameObjectListener} is provides an interface to react to events of
 * a {@code GameObject}.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public interface GameObjectListener {

    /**
     * This method is called when the game object gets destroyed.
     *
     * @param event A game object event
     */
    default public void onDestroy(GameObjectEvent event) {}

}
