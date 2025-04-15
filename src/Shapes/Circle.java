package Shapes;

public class Circle implements Figure{
    private double radius;

    public Circle(double r){
        setRadius(r);
    }

    private void setRadius(double r){
        if(r <= 0){
            throw new IllegalArgumentException("Radius must be greater than 0");
        }
        this.radius = r;
    }

    @Override
    public double perimeter(){
        double perimeter = 2 * Math.PI * radius;
        if(Double.isInfinite(perimeter)){
            throw new ArithmeticException("Overflow: Perimeter exceeds max val");
        }
        return perimeter;
    }

    @Override
    public String toString(){
        return String.format("circle %.2f", radius);
    }

    @Override
    public Circle clone() {
        try {
            Circle clone = (Circle) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
