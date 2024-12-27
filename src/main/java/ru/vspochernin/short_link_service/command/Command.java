package ru.vspochernin.short_link_service.command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.User;

public record Command(
        CommandType commandType,
        List<String> arguments)
{

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String DELIMITER_REGEX = " +";

    public static Command getNext() {
        System.out.println("------------------------");
        System.out.print("Введите очередную команду [" + getCurrentUserString() + "]: ");

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

        return new Command(commandType, arguments);
    }

    private static String getCurrentUserString() {
        return ShortLinkContext.currentUser
                .map(User::getId)
                .map(UUID::toString)
                .orElse("неидентифицирован");
    }
}
