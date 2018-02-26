package breakout;

import newton.geometry.Circle;
import newton.geometry.Rectangle;

public class IntersectionTest {

    public static void main(String[] args) {

        Circle c1 = new Circle(0, 0, 100);
        Rectangle r1 = new Rectangle(50, 50, 50, 50);

        System.out.println(c1.getBounds().getHeight());

        boolean test = c1.intersects(r1);

        System.out.println(test ? "yo" : "ney");

        Class<?> c = java.awt.Point.class;

    }

}
