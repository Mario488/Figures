package Shapes;

public class Rectangle implements Figure{
    private double width, height;

    public Rectangle(double w, double h){
        setSides(w, h);
    }
    private void setSides(double w, double h){
        if(w <= 0 || h <= 0){
            throw new IllegalArgumentException("Width and height must be greater than 0");
        }
        this.width = w;
        this.height = h;
    }

    @Override
    public String toString(){
        return String.format("rectangle %.2f %.2f", width, height);
    }

    @Override
    public double perimeter() {
        double perimeter = 2 * (width + height);
        if(Double.isInfinite(perimeter)){
            throw new ArithmeticException("Overflow: Perimeter exceeds max val");
        }
        return perimeter;
    }

    @Override
    public Rectangle clone() {
        try {
            Rectangle clone = (Rectangle) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}