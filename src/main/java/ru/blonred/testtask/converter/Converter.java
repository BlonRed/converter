package ru.blonred.testtask.converter;

import java.util.*;

abstract class Unit {
    String name;

    //This contains all relations of this unit to others written in the buffer
    private final Map<String, Double> listRatio = new HashMap<>();

    public Unit(String name) {
        this.name = name;
    }

    //Setter ratio
    public void addRatio(String name, double ratio) {
        listRatio.put(name, ratio);
    }

    //Getter ratio
    public Double getRatio(String name) {
        return listRatio.get(name);
    }

    //Check if there is a relation in the buffer of this unit
    public boolean hasThisRatio(String name) {
        return (listRatio.get(name) != null);
    }

    //Checking for matching relations in the buffer of this unit with the received
    public String searchCoincidence(Unit unitSecond) {

        for (Map.Entry<String, Double> mapUnitFirst : this.listRatio.entrySet()) {

            String nameFirst = mapUnitFirst.getKey();

            for (Map.Entry<String, Double> mapUnitSecond : unitSecond.listRatio.entrySet()) {

                String nameSecond = mapUnitSecond.getKey();

                if (nameFirst.equals(nameSecond)) {
                    return nameFirst;
                }
            }
        }
        return null;
    }
}

class Converter {
    //This contains all the units written in the buffer
    private final Map<String, Unit> units = new HashMap<>();

    //#1 of the basic methods of this class
    public void processInput(String input) {
        Scanner scanner = new Scanner(input);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            //Processing of received data from the input stream
            if (line.isEmpty()) continue;

            String[] parts = line.split(" ");

            if (parts.length != 5 || !parts[2].equals("=")) {
                System.out.println("Invalid input: " + line);
                continue;
            }

            if (isNumber(parts[0])) {
                if (parts[3].equals("?")) {
                    //Start of branching at the conversion request
                    queryProcessing(parts);
                } else if (isNumber(parts[3])) {
                    //Start of branching when entering data on correlation between units
                    recordProcessing(parts);
                } else {
                    System.out.println("Invalid input: " + line);
                }
            }
        }
    }

    //#2 of the basic methods of this class
    public void recordProcessing(String[] parts) {

        //Highlighting the name and dimensionality of the first unit
        String unitNameFirst = parts[1];
        double unitValueFirst = Double.parseDouble(parts[0]);

        //Highlighting the name and dimensionality of the second unit
        String unitNameSecond = parts[4];
        double unitValueSecond = Double.parseDouble(parts[3]);

        //Getting the ratio of the first unit to the second unit
        double ratioForFirst = unitValueSecond / unitValueFirst;
        //and vice versa
        double ratioForSecond = unitValueFirst / unitValueSecond;

        //Creating units in the buffer
        if (units.get(unitNameFirst) == null) {
            addUnit(unitNameFirst);
        }
        if (units.get(unitNameSecond) == null) {
            addUnit(unitNameSecond);
        }

        //Writing into a separate object of the first unit of the relation to the second unit
        if (!units.get(unitNameFirst).hasThisRatio(unitNameSecond)) {
            units.get(unitNameFirst).addRatio(unitNameSecond, ratioForFirst);
        }
        //and vice versa
        if (!units.get(unitNameSecond).hasThisRatio(unitNameFirst)) {
            units.get(unitNameSecond).addRatio(unitNameFirst, ratioForSecond);
        }
    }

    //#3 of the basic methods of this class
    public void queryProcessing(String[] parts) {
        double value;

        //Value highlighting
        value = Double.parseDouble(parts[0]);

        //Highlighting the units name
        String unitFromName = parts[1];
        String unitToName = parts[4];

        //Conversion start and show result
        String result = convert(value, unitFromName, unitToName);
        System.out.println(result);
    }

    //#4 of the basic methods of this class
    public String convert(double value, String unitNameFrom, String unitNameTo) {
        //Getting unit objects from the buffer
        Unit fromUnit = units.get(unitNameFrom);
        Unit toUnit = units.get(unitNameTo);

        if (fromUnit == null || toUnit == null) {
            return "Conversion not possible.";
        }

        if (fromUnit == toUnit) {
            return String.format("%.2f %s = %.2f %s", value, fromUnit.name, value, toUnit.name);
        }

        //Conversion calculation
        if (fromUnit.hasThisRatio(unitNameTo)) {
            //If the unit object already has information about their relation
            double result = fromUnit.getRatio(unitNameTo) * value;
            return String.format("%.2f %s = %.2f %s", value, fromUnit.name, result, toUnit.name);
        } else if (fromUnit.searchCoincidence(toUnit) != null) {
            //If the object does not yet have information about their relation
            //Search for coincidence
            String nameCoincidence = fromUnit.searchCoincidence(toUnit);
            Unit coincidenceUnit = units.get(nameCoincidence);
            double result = fromUnit.getRatio(nameCoincidence) * coincidenceUnit.getRatio(unitNameTo) * value;
            //Writing ratio between unit objects, on future
            String str = value + " " + unitNameFrom + " " + "=" + " " + result + " " + unitNameTo;
            processInput(str);

            return String.format("%.2f %s = %.2f %s", value, fromUnit.name, result, toUnit.name);
        }

        return "Conversion not possible.";
    }

    //helper method for creating a unit in the buffer
    public void addUnit(String name) {
        units.put(name, new Unit(name) {
        });
    }

    //helper method
    public boolean isNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number: " + str);
            return false;
        }
    }
}

class MainConverter {
    public static void main(String[] args) {
        Converter converter = new Converter();
        System.out.println("To record data on the ratio between " +
                "the units of measurement - " +
                "enter the data in the format: \"1 kg = 1000 g\"");
        System.out.println("To get the conversion result, enter the data in the format: \"2 kg = ? g\"");
        System.out.println("To close the program, enter: \"end\"");

        //All the work of the program is carried out through the input stream
        //When data is received, data processing begins immediately
        Scanner scanner = new Scanner(System.in);
        do {
            String input = scanner.nextLine().trim();

            //Condition for completing the program
            if (input.equals("end")) {
                break;
            }

            converter.processInput(input);
        } while (scanner.hasNextLine());
    }
}