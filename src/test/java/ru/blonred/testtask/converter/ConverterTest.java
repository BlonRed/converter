package ru.blonred.testtask.converter;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConverterTest {
    final String s = System.lineSeparator();
    Converter temp = new Converter();

    @Test
    void test1ProcessInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expectedOutput = "3,00 kg = 3000,00 g" + s;
        temp.processInput("1 kg = 1000 g");
        temp.processInput("3 kg = ? g");

        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    void test2ProcessInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expectedOutput = "Invalid input: 1kg = 1000g" + s;
        temp.processInput("1kg = 1000g");

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test3ProcessInput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expectedOutput = "Invalid number: One" + s;
        temp.processInput("One kg = 1000 g");

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testRecordProcessingAndQueryProcessing() {
        String[] str1 = {"1000","kg","=","1","t"};
        temp.recordProcessing(str1);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String[] str2 = {"5","t","=","?","kg"};
        temp.queryProcessing(str2);

        String expectedOutput = "5,00 t = 5000,00 kg" + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test1QueryProcessing() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String[] str = {"5","cat","=","?","dog"};
        temp.queryProcessing(str);

        String expectedOutput = "Conversion not possible." + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test2QueryProcessing() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String[] str = {"1", "null", "null", "1", "null"};
        temp.queryProcessing(str);

        String expectedOutput = "Conversion not possible." + s;

        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void test1Convert() {
        String[] str = {"1000","kg","=","1","t"};

        String expected = "2,00 t = 2000,00 kg";

        temp.recordProcessing(str);

        assertEquals(expected, temp.convert(2, str[4], str[1]));
    }

    @Test
    void test2Convert() {
        String[] str = {"1000","kg","=","1","t"};

        String expected = "5,00 t = 5,00 t";

        temp.recordProcessing(str);

        assertEquals(expected, temp.convert(5, str[4], str[4]));
    }

    @Test
    void test3Convert() {
        String[] str = {"1000","kg","=","1","t"};

        String expected = "Conversion not possible.";

        temp.recordProcessing(str);

        assertEquals(expected, temp.convert(5, "cat", "dog"));
    }

    @Test
    void test4Convert() {
        String[] str1 = {"1000","kg","=","1","t"};
        String[] str2 = {"1","kg","=","1000","g"};

        String expected = "5,00 t = 5000000,00 g";

        temp.recordProcessing(str1);
        temp.recordProcessing(str2);

        assertEquals(expected, temp.convert(5, str1[4], str2[4]));
    }

    @Test
    void testAddUnit() {
        temp.addUnit("cat");
    }

    @Test
    void test1IsNumber() {
        assertTrue(temp.isNumber("5"));
    }

    @Test
    void test2IsNumber() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String str = "string";
        String expectedOutput = "Invalid number: " + str + s;

        assertFalse(temp.isNumber(str));
        assertEquals(expectedOutput, outputStream.toString());
    }
}