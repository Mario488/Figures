package Shapes;

import java.io.InputStream;

public class AbstractFigureFactory {
    public static FigureFactory getFactory(String inputType){
        if(inputType.equalsIgnoreCase("random")){
            return new RandomFigureFactory();
        }
        throw new IllegalArgumentException("Unknown type: " + inputType);
    }

    public static FigureFactory getFactory(String inputType, InputStream stream){
        if(inputType.equalsIgnoreCase("stream")){
            return new StreamFigureFactory(stream);
        }
        throw new IllegalArgumentException("Unknown type: " + inputType);
    }
}
