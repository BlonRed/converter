package ru.blonred.testtask.converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest extends TestService{
    @Test
    void testProcessed() {
        recordTextFromConsole();
        String input = "1 kg = 1000 g";
        converter.processed(input);
        input = "3 kg = ? g";
        converter.processed(input);
        closeRecordFromConsole();

        String expectedOutput = "3,00 kg = 3000,00 g" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test2Processed() {
        recordTextFromConsole();
        String input = "1 t = ? kg";
        converter.processed(input);
        closeRecordFromConsole();

        String expectedOutput = "Conversion not possible." + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test3Processed() {
        recordTextFromConsole();
        String input = "1000 kg = 1 t";
        converter.processed(input);
        input = "1 kg = 1000 g";
        converter.processed(input);
        input = "5 t = ? g";
        converter.processed(input);
        closeRecordFromConsole();

        String expectedOutput = "5,00 t = 5000000,00 g" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
    @Test
    void test4Processed() {
        recordTextFromConsole();
        String input = "5 cat = ? dog";
        converter.processed(input);
        closeRecordFromConsole();

        String expectedOutput = "Conversion not possible." + s;
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test5Processed() {
        recordTextFromConsole();
        String input = "null null null null null";
        converter.processed(input);
        closeRecordFromConsole();

        String expectedOutput = "Invalid input: null null null null null" + s;
        assertEquals(expectedOutput, outputStream.toString());
    }
}