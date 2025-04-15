package Shapes;
import java.util.Random;

public class RandomFigureFactory implements FigureFactory{
    private final Random rand = new Random();
    public static final int maxRandVal = 1000;
    public static final String[] types = {"triangle", "rectangle", "circle"};

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

    private Figure createTriangle(){
        double A = rand.nextDouble(maxRandVal);
        double B = rand.nextDouble(maxRandVal);
        // c satisfies triangle inequality: |a - b| < c < a + b
        double min = Math.abs(A - B) + 0.01;
        double max = A + B - 0.01;
        double C = rand.nextDouble(min, max);
        try{
            return new Triangle(A, B, C);
        }
        catch(InvalidTriangleException e){
            throw new RuntimeException("Generated invalid triangle");
        }
    }

    private Figure createRectangle(){
        double width = rand.nextDouble(maxRandVal);
        double height = rand.nextDouble(maxRandVal);
        return new Rectangle(width, height);
    }

    private Figure createCircle(){
        double radius = rand.nextDouble(maxRandVal);
        return new Circle(radius);
    }
}
