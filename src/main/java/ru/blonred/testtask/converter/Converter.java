package ru.blonred.testtask.converter;

import ru.blonred.testtask.converter.model.*;

public class Converter {
    private UnitsContainer unitsContainer;

    public Converter() {
        unitsContainer = new UnitsContainer();
    }
    public Converter(UnitsContainer unitsContainer) {
        this.unitsContainer = unitsContainer;
    }

    public void setUnitsContainer(UnitsContainer unitsContainer) {
        this.unitsContainer = unitsContainer;
    }
    public UnitsContainer getUnitsContainer() {
        return unitsContainer;
    }

    public void processed(String input) {
        InputObject io = InputHandler.handlingOfInput(input);
        if (io == null) {
            return;
        }
        if (io.isQueryToConvert()) {
            String answer = convert(io);
            System.out.println(answer);
        } else {
            try {
                unitsContainer.recordingTheRatioOfUnits(io);
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }
        }
    }

    private String convert(InputObject io) {
        double value = io.getFirstValue();
        String unitNameFrom = io.getFirstUnitName();
        String unitNameTo = io.getSecondUnitName();
        Unit unitFrom = unitsContainer.getContainer().get(unitNameFrom);
        Unit unitTo = unitsContainer.getContainer().get(unitNameTo);

        if (unitFrom == null || unitTo == null) {
            return "Conversion not possible.";
        }
        if (unitFrom == unitTo) {
            return String.format("%.2f %s = %.2f %s", value, unitNameFrom, value, unitNameTo);
        }
        if (unitFrom.hasThisRatio(unitNameTo)) {
            double result = unitFrom.getRatio(unitNameTo) * value;
            return String.format("%.2f %s = %.2f %s", value, unitNameFrom, result, unitNameTo);
        } else if (unitFrom.searchCoincidence(unitTo) != null) {
            String nameCoincidence = unitFrom.searchCoincidence(unitTo);
            Unit coincidenceUnit = unitsContainer.getContainer().get(nameCoincidence);
            double result = unitFrom.getRatio(nameCoincidence) * coincidenceUnit.getRatio(unitNameTo) * value;
            InputObject newRecord = new InputObject(value, unitNameFrom, result, unitNameTo);
            try {
                unitsContainer.recordingTheRatioOfUnits(newRecord);
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }
            return String.format("%.2f %s = %.2f %s", value, unitNameFrom, result, unitNameTo);
        }
        return "Conversion not possible.";
    }
}