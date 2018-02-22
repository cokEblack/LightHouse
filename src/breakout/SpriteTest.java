package breakout;

import breakout.game.texture.SpriteMap;
import breakout.game.texture.SpriteMapping;
import breakout.physics.geometry.Rectangle;
import lighthouse.GreatestLevel;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class SpriteTest {

    public static void main(String[] args) {

        SpriteMap map = new SpriteMap();
        map.add(new SpriteMapping(new Rectangle(0, 0, 100, 100)));
        map.add(new SpriteMapping(new Rectangle(100, 0, 100, 100)));
        map.add(new SpriteMapping(new Rectangle(200, 0, 100, 100)));

        try {
            JAXBContext context = JAXBContext.newInstance(SpriteMap.class);
            /*
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(map, System.out);
            */

            Unmarshaller unmarshaller = context.createUnmarshaller();
            SpriteMap testmap = (SpriteMap) unmarshaller.unmarshal(GreatestLevel.class.getResource("resources/sprite.mappings.xml"));

            System.out.println(testmap);

        } catch (JAXBException exception) {
            exception.printStackTrace();
        }


    }

}
