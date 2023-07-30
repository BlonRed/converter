package ru.blonred.testtask.converter.model;

import ru.blonred.testtask.converter.InputHandler;

public class InputObject {
    private final double firstValue;
    private final String firstUnitName;
    private final Double secondValue;
    private final String secondUnitName;
    private boolean isQueryToConvert;

    public InputObject(double firstValue, String firstUnitName, Double secondValue, String secondUnitName) {
        this.firstValue = firstValue;
        this.firstUnitName = firstUnitName;
        this.secondValue = secondValue;
        this.secondUnitName = secondUnitName;
        if (secondValue == null) {
            this.isQueryToConvert = true;
        }
    }
    public InputObject(Double firstValue, String firstUnitName, String secondUnitName) {
        this.firstValue = firstValue;
        this.firstUnitName = firstUnitName;
        this.secondValue = null;
        this.secondUnitName = secondUnitName;
        if (secondValue == null) {
            this.isQueryToConvert = true;
        }
    }

    public String getFirstUnitName() {
        return firstUnitName;
    }

    public String getSecondUnitName() {
        return secondUnitName;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public Double getSecondValue() {
        return secondValue;
    }

    public boolean isQueryToConvert() {
        return isQueryToConvert;
    }
}
