package breakout.util;

/**
 * A builder provides a fluent interface to construct objects which are
 * highly customizable.
 *
 * @author Melf Kammholz
 *
 * @param <T> The type of the object this builder can construct
 */
public interface Builder <T> {

    /**
     * Returns a reference to the object that has been constructed by this builder.
     *
     * @return The object that has been constructed by this builder
     * @throws InstantiationException If the an exception occurred while building
     *
     */
    T build() throws InstantiationException;

}
