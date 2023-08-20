package ru.blonred.testtask.converter;

import org.junit.jupiter.api.Test;
import ru.blonred.testtask.converter.model.InputObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitsContainerTest extends TestService {
    @Test
    void test1RecordProcessingAndQueryProcessing() {
        InputObject inputObject = new InputObject(1000., "kg", 1., "t");
        try {
            unitsContainer.recordingTheRatioOfUnits(inputObject);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        String input = "5 t = ? kg";
        converter.processed(input);

        String expectedOutput = "5,00 t = 5000,00 kg" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test2RecordProcessingAndQueryProcessing() {
        InputObject inputObject1 = new InputObject(1000., "kg", 1., "t");
        InputObject inputObject2 = new InputObject(0.5, "kg", 500., "g");
        try {
            unitsContainer.recordingTheRatioOfUnits(inputObject1);
            unitsContainer.recordingTheRatioOfUnits(inputObject2);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }
        String input = "2.5 t = ? g";
        converter.processed(input);

        String expectedOutput = "2,50 t = 2500000,00 g" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test3RecordProcessingAndQueryProcessing() {
        InputObject inputObject = new InputObject(1000., "kg", null, "t");
        try {
            unitsContainer.recordingTheRatioOfUnits(inputObject);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        String expectedOutput = "Invalid Input Data for record: second unit`s value." + s;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test4RecordProcessingAndQueryProcessing() {
        InputObject inputObject = new InputObject(1000., "kg", "t");
        try {
            unitsContainer.recordingTheRatioOfUnits(inputObject);
        } catch (Exception exc) {
            System.out.println(exc.getMessage());
        }

        String expectedOutput = "Invalid Input Data for record: second unit`s value." + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
}
