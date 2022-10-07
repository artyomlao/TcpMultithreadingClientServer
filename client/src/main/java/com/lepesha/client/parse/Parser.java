package com.lepesha.client.parse;

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
