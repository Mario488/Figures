package tests;

import Shapes.Circle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CircleTest {
    @Test
    public void testCircleConstructor(){
        assertDoesNotThrow(() -> new Circle(3));
        assertDoesNotThrow(() -> new Circle(5.5));
        assertDoesNotThrow(() -> new Circle(9.5239823));

        assertThrows(IllegalArgumentException.class, () -> new Circle(0));
        assertThrows(IllegalArgumentException.class, () -> new Circle(-5));
        assertThrows(IllegalArgumentException.class, () -> new Circle(-56.5892));
    }

    @Test
    public void testCirclePerimeter(){
        Circle c1 = new Circle(0.14516);
        Circle c2 = new Circle(3);
        Circle c3 = new Circle(5.5);
        Circle c4 = new Circle(9.5239823);
        Circle c5 = new Circle(5619.712);
        Circle c6 = new Circle(8.98846567431158e306);
        Circle c7 = new Circle(8.98846567431158e307);


        assertEquals(0.912067, c1.perimeter(), 0.01);
        assertEquals(18.84956, c2.perimeter(), 0.01);
        assertEquals(34.5575, c3.perimeter(), 0.01);
        assertEquals(59.841, c4.perimeter(), 0.01);
        assertEquals(35309.7, c5.perimeter(), 0.01);
        assertDoesNotThrow(c6::perimeter);
        assertThrows(ArithmeticException.class, c7::perimeter);
    }

    @Test
    public void testCircleToString(){
        Circle c1 = new Circle(0.14516);
        Circle c2 = new Circle(3);
        Circle c3 = new Circle(5.5);
        Circle c4 = new Circle(9.5239823);
        Circle c5 = new Circle(5619.712);

        assertEquals("circle 0.15", c1.toString());
        assertEquals("circle 3.00", c2.toString());
        assertEquals("circle 5.50", c3.toString());
        assertEquals("circle 9.52", c4.toString());
        assertEquals("circle 5619.71", c5.toString());
    }

    @Test
    public void testCircleClone(){
        Circle c1 = new Circle(5.5);
        Circle c2 = c1.clone();

        assertNotSame(c1, c2);
        assertEquals(c1.perimeter(), c2.perimeter(), 0.01);
        assertEquals(c1.toString(), c2.toString());

        c1 = new Circle(11.9);
        assertNotEquals(c1.perimeter(), c2.perimeter());
    }

}
