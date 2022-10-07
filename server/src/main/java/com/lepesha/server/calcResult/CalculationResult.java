package com.lepesha.server.calcResult;

public class CalculationResult {
    public static String calculateQuarter(double[] points) {
        double x = points[0];
        double y = points[1];

        if(x>0 && y>0) {
            return x + ";" + y + ";" + 1;
        } else if(x < 0 && y > 0) {
            return x + ";" + y + ";" + 2;
        } else if(x < 0 && y < 0) {
            return x + ";" + y + ";" + 3;
        } else if(x > 0 && y < 0) {
            return x + ";" + y + ";" + 4;
        }

        return x + ";" + y + ";" + 0;
    }
}
