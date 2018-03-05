package breakout.game.gameobject;

import java.util.HashMap;
import java.util.Map;

public class GameplayAttributeMap {

    public static enum DefaultAttribute {

        HEALTH("health");

        private String name;

        private DefaultAttribute(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

    }

    private final Map<String, GameplayAttribute> attributes = new HashMap<>();


    public void setAttribute(DefaultAttribute name, GameplayAttribute attribute) {
        setAttribute(name.getName(), attribute);
    }

    public void setAttribute(String name, GameplayAttribute attribute) {
        attributes.put(name, attribute);
    }

    public GameplayAttribute getAttribute(DefaultAttribute name) {
        return getAttribute(name.getName());
    }

    public GameplayAttribute getAttribute(String name) {
        return attributes.get(name);
    }

}
