package breakout.util;

public class GenericNamingStrategy implements NamingStrategy {

    private final String prefix;
    private int counter = 0;

    public GenericNamingStrategy(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String createName() {
        return String.format("%s #%d", this.prefix, ++counter);
    }

}
