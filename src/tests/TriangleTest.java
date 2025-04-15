package tests;

import Shapes.Triangle;
import Shapes.InvalidTriangleException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @Test
    public void testTriangleConstructor(){
        assertDoesNotThrow(() -> new Triangle(3, 4, 5));
        assertDoesNotThrow(() -> new Triangle(6, 6, 6));
        assertDoesNotThrow(() -> new Triangle(10, 10, 15));
        assertDoesNotThrow(() -> new Triangle(1.5, 2.5, 3.0));
        assertDoesNotThrow(() -> new Triangle(456.418, 571.519, 345.397));

        assertThrows(InvalidTriangleException.class, () -> new Triangle(10, 10, 20));
        assertThrows(IllegalArgumentException.class, () -> new Triangle(-5, 4, 3));
        assertThrows(InvalidTriangleException.class, () -> new Triangle(1, 2, 3));
        assertThrows(InvalidTriangleException.class, () -> new Triangle(5, 2, 2));
    }

    @Test
    public void testTrianglePerimeter() throws InvalidTriangleException {
        Triangle tr1 = new Triangle(3, 4, 5);
        Triangle tr2 = new Triangle(6, 6, 6);
        Triangle tr3 = new Triangle(10, 10, 15);
        Triangle tr4 = new Triangle(1.5, 2.5, 3.0);
        Triangle tr5 = new Triangle(456.418, 571.519, 345.397);
        Triangle tr6 = new Triangle(7.98846567431158e300, 6.98846567431158e300, 1.398646567431158e301);
        Triangle tr7 = new Triangle(8.98846567431158e307, 8.98846567431158e307, 8.98846567431158e306);

        assertEquals(12.0, tr1.perimeter(), 0.01);
        assertEquals(18.0, tr2.perimeter(), 0.01);
        assertEquals(35, tr3.perimeter(), 0.01);
        assertEquals(7.0, tr4.perimeter(), 0.01);
        assertEquals(1373.334, tr5.perimeter(), 0.01);
        assertDoesNotThrow(tr6::perimeter);
        assertThrows(ArithmeticException.class, tr7::perimeter);
    }

    @Test
    public void testTriangleToString() throws InvalidTriangleException {
        Triangle tr1 = new Triangle(3, 4, 5);
        Triangle tr2 = new Triangle(6, 6, 6);
        Triangle tr3 = new Triangle(10, 10, 15);
        Triangle tr4 = new Triangle(1.5, 2.5, 3.0);
        Triangle tr5 = new Triangle(456.418, 571.519, 345.397);


        assertEquals("triangle 3.00 4.00 5.00", tr1.toString());
        assertEquals("triangle 6.00 6.00 6.00", tr2.toString());
        assertEquals("triangle 10.00 10.00 15.00", tr3.toString());
        assertEquals("triangle 1.50 2.50 3.00", tr4.toString());
        assertEquals("triangle 456.42 571.52 345.40", tr5.toString());

    }

    @Test
    public void testTriangleClone() throws InvalidTriangleException {
        Triangle t1 = new Triangle(3, 4, 5);
        Triangle t2 = t1.clone();

        assertNotSame(t1, t2);
        assertEquals(t1.perimeter(), t2.perimeter(), 0.01);
        assertEquals(t1.toString(), t2.toString());

        t1 = new Triangle(10, 10, 15);
        assertNotEquals(t1.perimeter(), t2.perimeter());
    }
}
