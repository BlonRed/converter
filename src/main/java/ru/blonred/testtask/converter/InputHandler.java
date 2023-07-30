package ru.blonred.testtask.converter;

import ru.blonred.testtask.converter.model.InputObject;

public class InputHandler {
    private static boolean isQueryForConvert = false;

    public static InputObject handlingOfInput(String input) {
        if (!isValid(input)){
            return null;
        }
        String[] parseInput = input.trim().split(" ");
        double valueFrom = Double.parseDouble(parseInput[0]);
        String unitNameFrom = parseInput[1];
        Double valueTo = isQueryForConvert ? null : Double.parseDouble(parseInput[3]);
        String unitNameTo = parseInput[4];
        return new InputObject(valueFrom, unitNameFrom, valueTo, unitNameTo);
    }

    public static boolean isValid(String input) {
        if (input.isEmpty()) {
            System.out.println("Input is Empty");
            return false;
        }
        String[] parts = input.split(" ");
        if (parts.length != 5 || !parts[2].equals("=")) {
            System.out.println("Invalid input: " + input);
            return false;
        }
        if (isNumber(parts[0])) {
            if ((isQueryForConvert = (parts[3].equals("?"))) || isNumber(parts[3])) {
                return true;
            } else {
                System.out.println("Invalid input: " + input);
            }
        }
        return false;
    }

    public static boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + str);
            return false;
        }
    }
}
