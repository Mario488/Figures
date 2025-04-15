package Shapes;

public class StringToFigure {

    private static Figure createFigureFromArgs(String type, double ...nums) throws InvalidTriangleException, InvalidStringToFigureException {
        switch(type){
            case "triangle":
                if(nums.length == 3){
                    return new Triangle(nums[0], nums[1], nums[2]);
                }
                throw new InvalidStringToFigureException("Triangle requires 3 sides");
            case "rectangle":
                if(nums.length == 2){
                    return new Rectangle(nums[0], nums[1]);
                }
                throw new InvalidStringToFigureException("Rectangle requires 2 sides");
            case "circle":
                if(nums.length == 1){
                    return new Circle(nums[0]);
                }
                throw new InvalidStringToFigureException("Circle requires radius");
            default:
                throw new InvalidStringToFigureException("Invalid figure type");
        }
    }

    public static Figure createFrom(String strFig) throws InvalidStringToFigureException, InvalidTriangleException {
        if (strFig == null || strFig.isBlank()) throw new InvalidStringToFigureException("Input can't be blank or null");
        String[] tokens = strFig.trim().split(" ");
        String type = tokens[0].toLowerCase();
        int argCount = tokens.length - 1;
        double[] nums = new double[argCount];

        for(int i = 0; i < argCount; i++){
            try{
                nums[i] = Double.parseDouble(tokens[i+1]);
            }
            catch(NumberFormatException e){
                throw new InvalidStringToFigureException("Invalid number format");
            }
        }
        return createFigureFromArgs(type, nums);
    }
}
