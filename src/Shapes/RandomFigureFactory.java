package Shapes;
import java.util.Random;
import java.util.Set;
import org.reflections.Reflections;


public class RandomFigureFactory implements FigureFactory{
    private final Random rand = new Random();
    public static final int maxRandVal = 1000;
    public static final String[] types = getFigureTypes();

    @Override
    public Figure create() {
        String type = types[rand.nextInt(types.length)];

        return switch (type) {
            case "triangle" -> createTriangle();
            case "rectangle" -> createRectangle();
            case "circle" -> createCircle();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    private static String[] getFigureTypes(){
        Reflections reflections = new Reflections("Shapes"); // replace with your actual package
        Set<Class<? extends Figure>> subTypes = reflections.getSubTypesOf(Figure.class);
        return subTypes.stream()
                .map(Class::getSimpleName)
                .map(String::toLowerCase)
                .toArray(String[]::new);
    }

    private Figure createTriangle(){
        double A = rand.nextDouble(maxRandVal);
        double B = rand.nextDouble(maxRandVal);
        // c satisfies triangle inequality: |a - b| < c < a + b
        double min = Math.abs(A - B) + 0.01;
        double max = A + B - 0.01;
        double C = rand.nextDouble(min, max);
        try{
            return new Triangle(new double[] {A, B, C});
        }
        catch(InvalidTriangleException e){
            throw new RuntimeException("Generated invalid triangle");
        }
    }

    private Figure createRectangle(){
        double width = rand.nextDouble(maxRandVal);
        double height = rand.nextDouble(maxRandVal);
        return new Rectangle(new double[] {width, height});
    }

    private Figure createCircle(){
        double radius = rand.nextDouble(maxRandVal);
        return new Circle(new double[] {radius});
    }
}
