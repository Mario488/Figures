package tests;

import java.io.*;
import java.nio.charset.StandardCharsets;
import Shapes.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Shapes.StreamFigureFactory;

public class StreamFigureFactoryTest {

    @Test
    public void createFigureTest() {
        String input = """
            triangle 3 4 5
            circle 4.5
            rectangle 9.4 4.6
            wrong input 
            triangle 456.418 571.519 345.397
            rectangle 545.93
            circle -3
            """;

        InputStream stream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        StreamFigureFactory fac = new StreamFigureFactory(stream);

        assertNotNull(fac.create()); // triangle
        assertNotNull(fac.create()); // circle
        assertNotNull(fac.create()); // rectangle

        assertNull(fac.create());    // wrong input

        assertNotNull(fac.create()); // triangle

        assertNull(fac.create());    // rectangle

        assertNull(fac.create());    // circle
    }


}
