package ru.blonred.testtask.converter;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    final String s = System.lineSeparator();
    private ByteArrayOutputStream outputStream;
    Converter converter = new Converter();

    void recordTextFromConsole() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }


    @Test
    void test1ProcessInput() {
        recordTextFromConsole();

        String expectedOutput = "3,00 kg = 3000,00 g" + s;
        InputDataHandler.processInput("1 kg = 1000 g");
        InputDataHandler.processInput("3 kg = ? g");

        try {
            outputStream.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    void test2ProcessInput() {
        recordTextFromConsole();

        String expectedOutput = "Invalid input: 1kg = 1000g" + s;
        InputDataHandler.processInput("1kg = 1000g");

        try {
            outputStream.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test()
    void test3ProcessInput() {
        recordTextFromConsole();

        String expectedOutput = "Invalid number: One" + s;
        InputDataHandler.processInput("One kg = 1000 g");

        try {
            outputStream.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testRecordProcessingAndQueryProcessing() {
        String[] str1 = {"1000", "kg", "=", "1", "t"};
        converter.recordProcessing(str1);

        recordTextFromConsole();

        String[] str2 = {"5", "t", "=", "?", "kg"};
        converter.queryProcessing(str2);

        String expectedOutput = "5,00 t = 5000,00 kg" + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test1QueryProcessing() {
        recordTextFromConsole();

        String[] str = {"5", "cat", "=", "?", "dog"};
        converter.queryProcessing(str);

        String expectedOutput = "Conversion not possible." + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test2QueryProcessing() {
        recordTextFromConsole();

        String[] str = {"1", "null", "null", "1", "null"};
        converter.queryProcessing(str);

        String expectedOutput = "Conversion not possible." + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test1Convert() {
        String[] str = {"1000", "kg", "=", "1", "t"};

        String expected = "2,00 t = 2000,00 kg";

        converter.recordProcessing(str);

        assertEquals(expected, converter.convert(2, str[4], str[1]));
    }

    @Test
    void test2Convert() {
        String[] str = {"1000", "kg", "=", "1", "t"};

        String expected = "5,00 t = 5,00 t";

        converter.recordProcessing(str);

        assertEquals(expected, converter.convert(5, str[4], str[4]));
    }

    @Test
    void test3Convert() {
        String[] str = {"1000", "kg", "=", "1", "t"};

        String expected = "Conversion not possible.";

        converter.recordProcessing(str);

        assertEquals(expected, converter.convert(5, "cat", "dog"));
    }

    @Test
    void test4Convert() {
        String[] str1 = {"1000", "kg", "=", "1", "t"};
        String[] str2 = {"1", "kg", "=", "1000", "g"};

        String expected = "5,00 t = 5000000,00 g";

        converter.recordProcessing(str1);
        converter.recordProcessing(str2);

        assertEquals(expected, converter.convert(5, str1[4], str2[4]));
    }

    @Test
    void test1IsNumber() {
        assertTrue(InputDataHandler.isNumber("5"));
    }

    @Test
    void test2IsNumber() {
        assertFalse(InputDataHandler.isNumber("five"));
    }

    @Test
    void test3IsNumber() {
        recordTextFromConsole();

        String str = "string";
        String expectedOutput = "Invalid number: " + str + s;
        InputDataHandler.isNumber("string");
        assertEquals(expectedOutput, outputStream.toString());
    }
}