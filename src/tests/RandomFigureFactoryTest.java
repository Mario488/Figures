package tests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import Shapes.*;

public class RandomFigureFactoryTest {
    @Test
    public void testCreateRandomFigure(){
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

    private void updateFigureCount(Figure tempFig, Map<String, Integer> figCounts){
        if(tempFig instanceof Triangle){
            figCounts.put("triangle", figCounts.get("triangle") + 1);
        }
        else if(tempFig instanceof Rectangle){
            figCounts.put("rectangle", figCounts.get("rectangle") + 1);
        }
        else if(tempFig instanceof Circle){
            figCounts.put("circle", figCounts.get("circle") + 1);
        }
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
