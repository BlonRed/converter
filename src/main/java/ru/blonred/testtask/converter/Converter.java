package ru.blonred.testtask.converter;

import java.util.*;

public class Converter {
    //This contains all the units written in the buffer
    private final Map<String, Unit> units = new HashMap<>();

    //#1 of the basic methods of this class
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
            units.put(unitNameFirst, new Unit(unitNameFirst) {});
        }
        if (units.get(unitNameSecond) == null) {
            units.put(unitNameSecond, new Unit(unitNameSecond) {});
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
            String[] str = {Double.toString(value), unitNameFrom, "=", Double.toString(result), unitNameTo};
            recordProcessing(str);

            return String.format("%.2f %s = %.2f %s", value, fromUnit.name, result, toUnit.name);
        }

        return "Conversion not possible.";
    }
}