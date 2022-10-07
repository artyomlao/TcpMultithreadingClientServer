package com.lepesha.server.parse;

import java.util.Arrays;

public class Parser {
    public static double[] parse(String input) {
        String[] splitResult = input.split(";");

        double[] doubleValues = Arrays.stream(splitResult)
                .mapToDouble(Double::parseDouble)
                .toArray();

        return doubleValues;
    }

}
