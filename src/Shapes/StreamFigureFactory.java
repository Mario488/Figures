package Shapes;

import java.io.*;
import Shapes.StringToFigure;
import Shapes.FigureFactory;

public class StreamFigureFactory implements FigureFactory{

    private final BufferedReader reader;

    public StreamFigureFactory(InputStream inputStream){
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public Figure create(){
        try{
            String line = reader.readLine();
            if (line != null && !line.isBlank()) {
                return StringToFigure.createFrom(line);
            }
        }
        catch(IOException | InvalidStringToFigureException | InvalidTriangleException | IllegalArgumentException  e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
