package Shapes;

import java.util.Locale;

public class Triangle implements Figure{
    private double sideA, sideB, sideC;

    public Triangle(double[] args) throws InvalidTriangleException {
        setSides(args);
    }
    private void setSides(double[] args) throws InvalidTriangleException {
        double A = args[0];
        double B = args[1];
        double C = args[2];
        if(A <= 0 || B <= 0 || C <= 0){
            throw new IllegalArgumentException("Sides must be greater than 0");
        }
        if(!isValidTriangle(A, B, C)){
            throw new InvalidTriangleException("Wrong length of sides");
        }
        this.sideA = A;
        this.sideB = B;
        this.sideC = C;
    }
    private boolean isValidTriangle(double A, double B, double C){
        return (A + B > C) && (A + C > B) && (B + C > A);
    }

    @Override
    public String toString(){
        return String.format(Locale.US, "triangle %.2f %.2f %.2f", sideA, sideB, sideC);
    }

    @Override
    public double perimeter() {
        double perimeter = sideA + sideB + sideC;
        if(Double.isInfinite(perimeter)){
            throw new ArithmeticException("Overflow: Perimeter exceeds max val");
        }
        return perimeter;
    }

    @Override
    public Triangle clone() {
        try {
            Triangle clone = (Triangle) super.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
