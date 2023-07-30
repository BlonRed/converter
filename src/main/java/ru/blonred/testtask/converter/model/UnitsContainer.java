package ru.blonred.testtask.converter.model;

import java.util.HashMap;
import java.util.Map;

public class UnitsContainer {
    private final Map<String, Unit> container = new HashMap<>();

    public Map<String, Unit> getContainer() {
        return container;
    }

    public void recordingTheRatioOfUnits(InputObject io) throws Exception{
        double valueFirst = io.getFirstValue();
        String firstUnitName = io.getFirstUnitName();
        double valueSecond = checkForNull(io.getSecondValue());
        String secondUnitName = io.getSecondUnitName();

        double ratioForFirst = valueSecond / valueFirst;
        double ratioForSecond = valueFirst / valueSecond;
        createUnitInContainer(firstUnitName);
        createUnitInContainer(secondUnitName);
        recordRatio(firstUnitName, ratioForFirst, secondUnitName);
        recordRatio(secondUnitName, ratioForSecond, firstUnitName);
    }

    private double checkForNull(Double value) throws Exception {
        if (value == null) {
            throw new Exception("Invalid Input Data for record: second unit`s value.");
        }
        return value;
    }
    private void createUnitInContainer(String nameUnit) {
        if (!container.containsKey(nameUnit)) {
            container.put(nameUnit, new Unit(nameUnit));
        }
    }
    private void recordRatio(String firstUnitName, double ratioForFirst, String secondUnitName) {
        if (!container.get(firstUnitName).hasThisRatio(secondUnitName)) {
            container.get(firstUnitName).addRatio(secondUnitName, ratioForFirst);
        }
    }

}
