package breakout.game.texture;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is mainly used to decode XML files that contain the mapping
 * information for a sprite.
 *
 * @author Melf Kammholz
 * @author Sebastian Regenstein
 *
 */
@XmlRootElement(name="SpriteMap")
@XmlSeeAlso({ SpriteMapping.class })
public class SpriteMap extends ArrayList<SpriteMapping> {

    /**
     * Creates a sprite map.
     *
     */
    public SpriteMap() {}

    /**
     * Returns a reference to itself.
     *
     * @return A reference to itself
     */
    @XmlElement(name="SpriteMapping")
    public List<SpriteMapping> getSpriteMap() {
        return this;
    }


}
