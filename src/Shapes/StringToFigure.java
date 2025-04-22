package Shapes;
import java.lang.reflect.*;

public class StringToFigure {
    private static Figure createFigureFromArgs(String type, double ...nums) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        type = "Shapes." + StringUtils.capitalize(type);
        Class<?> cl = Class.forName(type);
        Constructor<?> ctor = cl.getConstructor(double[].class);
        Object[] args = new Object[]{nums};
        Object obj = ctor.newInstance(args);
        return (Figure) obj;
    }

    public static Figure createFrom(String strFig) throws InvalidStringToFigureException, InvalidTriangleException,
                                                          ClassNotFoundException, InvocationTargetException,
                                                          NoSuchMethodException, InstantiationException, IllegalAccessException
    {
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
