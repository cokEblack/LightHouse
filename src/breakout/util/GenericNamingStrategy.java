package breakout.util;

/**
 * A {@code GenericNamingStrategy} generates names by concatenating a number to
 * a given prefix, that is incremented each time the {@code createName} method
 * is called.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
public class GenericNamingStrategy implements NamingStrategy {

    /** The prefix of the name */
    private final String prefix;

    /** The counter */
    private int counter = 0;

    /**
     * Creates a naming strategy with a prefix.
     *
     * @param prefix A prefix
     */
    public GenericNamingStrategy(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String createName() {
        return String.format("%s #%d", this.prefix, ++counter);
    }

}
