package ru.vspochernin.short_link_service.command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public record Command(
        CommandType commandType,
        List<String> arguments)
{

    public static final Scanner scanner = new Scanner(System.in);
    public static final String DELIMITER_REGEX = " +";

    public static Command getNext() {
        System.out.println("------------------------");
        System.out.print("Введите очередную команду: ");

        String str = scanner.nextLine().trim();
        List<String> strParts = Arrays.stream(str.split(DELIMITER_REGEX))
                .toList();

        CommandType commandType = strParts.stream()
                .findFirst()
                .map(CommandType::parse)
                .orElse(CommandType.UNKNOWN);
        List<String> arguments = strParts.stream()
                .skip(1)
                .toList();

        System.out.println("DEBUG [считана команда]: " + commandType + " " + arguments);

        return new Command(commandType, arguments);
    }

    public static Command getUnknown() {
        return new Command(CommandType.UNKNOWN, List.of());
    }
}
