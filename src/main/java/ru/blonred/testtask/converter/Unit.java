package ru.blonred.testtask.converter;

import java.util.HashMap;
import java.util.Map;

public abstract class Unit {
    String name;

    //This contains all relations of this unit to others written in the buffer
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
