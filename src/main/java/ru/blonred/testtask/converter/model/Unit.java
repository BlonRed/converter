package ru.blonred.testtask.converter.model;

import java.util.HashMap;
import java.util.Map;

public class Unit {
    private final String name;
    private final Map<String, Double> listRatio = new HashMap<>();

    public Unit(String name) {
        this.name = name;
    }

    public void addRatio(String name, double ratio) {
        listRatio.put(name, ratio);
    }
    public Double getRatio(String name) {
        return listRatio.get(name);
    }
    public String getName() {
        return name;
    }
    public boolean hasThisRatio(String name) {
        return (listRatio.get(name) != null);
    }
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
