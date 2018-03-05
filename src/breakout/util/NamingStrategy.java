package breakout.util;

/**
 * A naming strategy used to create names dynamically.
 *
 * @author Melf Kammholz
 */
public interface NamingStrategy {

    /**
     * Creates a name.
     *
     * @return A new name
     */
    String createName();

}
