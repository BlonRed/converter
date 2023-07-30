package ru.blonred.testtask.converter;

import ru.blonred.testtask.converter.model.UnitsContainer;
import java.util.Scanner;

public class MainConvertor {
    public static void main(String[] args) {
        System.out.println("To record data on the ratio between the units of measurement - " +
                "enter the data in the format: \"1 kg = 1000 g\"\n" +
                "To get the conversion result, enter the data in the format: \"2 kg = ? g\"\n" +
                "To close the program, enter: \"end\"");
        UnitsContainer unitsContainer = new UnitsContainer();
        Converter converter = new Converter(unitsContainer);
        Scanner scanner = new Scanner(System.in);
        do {
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }
            converter.processed(input);
        } while (scanner.hasNextLine());
    }
}
