package tests;

import Shapes.Rectangle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RectangleTest {
    @Test
    public void testRectangleConstructor(){
        assertDoesNotThrow(() -> new Rectangle(5, 9));
        assertDoesNotThrow(() -> new Rectangle(5.5, 6.9));
        assertDoesNotThrow(() -> new Rectangle(6513.4194, 8714.7198));

        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 5));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-5, -4));
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(-56.5892, 56.5892));
    }


    @Test
    public void testRectanglePerimeter(){
        Rectangle r1 = new Rectangle(0.14156, 0.14516);
        Rectangle r2 = new Rectangle(3, 8);
        Rectangle r3 = new Rectangle(4.34, 8.97);
        Rectangle r4 = new Rectangle(9.5239823, 14.5191497);
        Rectangle r5 = new Rectangle(5619.712, 2301.879);
        Rectangle r6 = new Rectangle(8.98846567431158e306, 6.98846567431158e307);
        Rectangle r7 = new Rectangle(8.98846567431158e307, 6.98846567431158e307);


        assertEquals(0.5734, r1.perimeter(), 0.01);
        assertEquals(22, r2.perimeter(), 0.01);
        assertEquals(26.62, r3.perimeter(), 0.01);
        assertEquals(48.09, r4.perimeter(), 0.01);
        assertEquals(15843.2, r5.perimeter(), 0.1);
        assertDoesNotThrow(r6::perimeter);
        assertThrows(ArithmeticException.class, r7::perimeter);
    }

    @Test
    public void testRectangleToString(){
        Rectangle r1 = new Rectangle(0.14156, 0.14516);
        Rectangle r2 = new Rectangle(3, 8);
        Rectangle r3 = new Rectangle(4.34, 8.97);
        Rectangle r4 = new Rectangle(9.5239823, 14.5191497);
        Rectangle r5 = new Rectangle(5619.712, 2301.879);

        assertEquals("rectangle 0.14 0.15", r1.toString());
        assertEquals("rectangle 3.00 8.00", r2.toString());
        assertEquals("rectangle 4.34 8.97", r3.toString());
        assertEquals("rectangle 9.52 14.52", r4.toString());
        assertEquals("rectangle 5619.71 2301.88", r5.toString());
    }

    @Test
    public void testRectangleClone(){
        Rectangle r1 = new Rectangle(4.5, 5.5);
        Rectangle r2 = r1.clone();

        assertNotSame(r1, r2);
        assertEquals(r1.perimeter(), r2.perimeter(), 0.01);
        assertEquals(r1.toString(), r2.toString());

        r1 = new Rectangle(6.56, 14.5);
        assertNotEquals(r1.perimeter(), r2.perimeter());
    }

}
