package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import Shapes.*;
import java.lang.reflect.*;


public class RandomFigureFactoryTest {
    @Test
    public void testCreateRandomFigure() throws ClassNotFoundException {
        final int N = 1000;
        FigureFactory fac = new RandomFigureFactory();
        Map<String, Integer> figCounts = setFigureCounts();
        Figure tempFig;
        for(int i = 0; i < N; i++){
            tempFig = fac.create();
            checkValuesInRange(tempFig);
            updateFigureCount(tempFig, figCounts);
        }
        checkFigureDistribution(figCounts, N);
    }


    private Map<String, Integer> setFigureCounts(){
        Map<String, Integer> figCounts = new HashMap<>();
        for(String type: RandomFigureFactory.types){
            figCounts.put(type, 0);
        }
        return figCounts;
    }

    private void checkValuesInRange(Figure fig){
        String[] tokens = fig.toString().split(" ");
        for(int j = 1; j < tokens.length; j++){
            double num = Double.parseDouble(tokens[j]);
            assertTrue(num >= 0 && num <= RandomFigureFactory.maxRandVal * 2,
                    "Number out of range: " + num);
        }
    }

    private void updateFigureCount(Figure tempFig, Map<String, Integer> figCounts) throws ClassNotFoundException {
        String[] types = RandomFigureFactory.types;
        String figClassType;
        for(String type: types){
            figClassType = "Shapes." + capitilise(type);
            Class<?> cl = Class.forName(figClassType);
            if(cl.isInstance(tempFig)){
                figCounts.put(type, figCounts.get(type) + 1);
            }
        }

    }

    private static String capitilise(String input){
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    private void checkFigureDistribution(Map<String, Integer> figCounts, int N){
        int expectedCount = N / 3;
        int tolerance = 50;

        for (Map.Entry<String, Integer> entry : figCounts.entrySet()) {
            int count = entry.getValue();
            assertTrue(Math.abs(count - expectedCount) <= tolerance,
                    "Figure type " + entry.getKey() + " has unexpected distribution: " + count);
        }
    }

}
