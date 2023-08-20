package ru.blonred.testtask.converter;

import org.junit.jupiter.api.Test;
import ru.blonred.testtask.converter.model.InputObject;

import static org.junit.jupiter.api.Assertions.*;

public class InputHandlerTest extends TestService {
    @Test
    void testHandlingOfInput() {
        InputObject inputObject = InputHandler.handlingOfInput("1kg = 1000g");

        String expectedOutput = "Invalid input: 1kg = 1000g" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test2HandlingOfInput() {
        InputHandler.handlingOfInput("One kg = 1000 g");

        String expectedOutput = "Invalid number: One" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test3HandlingOfInput() {
        InputHandler.handlingOfInput("random text for test");

        String expectedOutput = "Invalid input: random text for test" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test4HandlingOfInput() {
        InputObject inputObject = InputHandler.handlingOfInput("1 kg = 1000 g");
        double firstValue = inputObject.getFirstValue();
        String firstUnitName = inputObject.getFirstUnitName();
        double secondValue = inputObject.getSecondValue();
        String secondUnitName = inputObject.getSecondUnitName();
        String result = String.format("%f %s = %f %s", firstValue, firstUnitName, secondValue, secondUnitName);
        String expectedOutput = "1,000000 kg = 1000,000000 g";
        assertEquals(expectedOutput, result);
    }

    @Test
    void test1IsNumber() {
        assertTrue(InputHandler.isNumber("5"));
    }

    @Test
    void test2IsNumber() {
        assertFalse(InputHandler.isNumber("five"));
    }

    @Test
    void test3IsNumber() {
        String str = "string";
        InputHandler.isNumber("string");
        String expectedOutput = "Invalid number: " + str + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
}
