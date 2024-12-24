package ru.vspochernin.short_link_service.command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ru.vspochernin.short_link_service.log.DebugLogger;

public record Command(
        CommandType commandType,
        List<String> arguments)
{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER_REGEX = " +";

    public static Command getNext() {
        System.out.println("------------------------");
        System.out.print("Введите очередную команду: ");

        String str = SCANNER.nextLine().trim();
        List<String> strParts = Arrays.stream(str.split(DELIMITER_REGEX))
                .toList();

        CommandType commandType = strParts.stream()
                .findFirst()
                .map(CommandType::parse)
                .orElse(CommandType.UNKNOWN);
        List<String> arguments = strParts.stream()
                .skip(1)
                .toList();

        DebugLogger.log("Считалась команда и аргументы: " + commandType + " " + arguments);

        return new Command(commandType, arguments);
    }
}
