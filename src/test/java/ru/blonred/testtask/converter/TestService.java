package ru.blonred.testtask.converter;

import ru.blonred.testtask.converter.model.UnitsContainer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public abstract class TestService {
    protected final String s = System.lineSeparator();
    protected ByteArrayOutputStream outputStream;
    protected UnitsContainer unitsContainer = new UnitsContainer();
    protected Converter converter = new Converter(unitsContainer);

    protected void recordTextFromConsole() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    protected void closeRecordFromConsole() {
        try {
            outputStream.close();
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
