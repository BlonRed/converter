package ru.blonred.testtask.converter;

public class InputDataHandler {
    public static void processInput(String input) {

        //Processing of received data from the input stream
        if (input.isEmpty()) return;

        String[] parts = input.split(" ");

        if (parts.length != 5 || !parts[2].equals("=")) {
            System.out.println("Invalid input: " + input);
            return;
        }

        if (isNumber(parts[0])) {
            if (parts[3].equals("?")) {
                //Start of branching at the conversion request
                MainConvertor.converter.queryProcessing(parts);
            } else if (isNumber(parts[3])) {
                //Start of branching when entering data on correlation between units
                MainConvertor.converter.recordProcessing(parts);
            } else {
                System.out.println("Invalid input: " + input);
            }
        }
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
