package tests;

import Shapes.InvalidStringToFigureException;
import Shapes.InvalidTriangleException;
import static Shapes.StringToFigure.createFrom;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringToFigureTest {

    @Test
    public void testCreateFrom() throws InvalidStringToFigureException, InvalidTriangleException {
        assertThrows(InvalidStringToFigureException.class, () -> createFrom(""));
        assertThrows(InvalidStringToFigureException.class, () -> createFrom("triangle 10 20"));
        assertThrows(InvalidStringToFigureException.class, () -> createFrom("circle 10 abc -30"));
        assertThrows(InvalidStringToFigureException.class, () -> createFrom("rectangle -10 20 30"));
        assertThrows(InvalidStringToFigureException.class, () -> createFrom("unknown 10 20 30"));

        assertDoesNotThrow(() -> createFrom("   rectangle 15.5 14.5"));
        assertDoesNotThrow(() -> createFrom("   triangle 3.5 4.5 5.5     "));
        assertDoesNotThrow(() -> createFrom("circle 9.5    "));
    }
}
