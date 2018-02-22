package breakout.game.texture;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * https://stackoverflow.com/questions/4152269/why-my-arraylist-is-not-marshalled-with-jaxb
 * https://stackoverflow.com/questions/1074069/format-xml-with-jaxb-during-unmarshal
 */
@XmlRootElement(name="SpriteMap")
@XmlSeeAlso({ SpriteMapping.class })
public class SpriteMap extends ArrayList<SpriteMapping> {

    public SpriteMap() {
    }

    @XmlElement(name="SpriteMapping")
    public List<SpriteMapping> getSpriteMap() {
        return this;
    }


}
