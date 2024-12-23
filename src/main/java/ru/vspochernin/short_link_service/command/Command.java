package ru.vspochernin.short_link_service.command;

import java.util.regex.Pattern;

public enum Command {

    UNKNOWN("^$"),
    EXIT("^exit$"),
    REGISTER("^register$"),
    ;

    private final String regex;

    Command(String regex) {
        this.regex = regex;
    }

    public static Command parse(String commandStr) {
        for (Command command : values()) {
            if (Pattern.compile(command.regex).matcher(commandStr).find()) {
                return command;
            }
        }
        return UNKNOWN;
    }

    public static Command getNext() {
        System.out.println("------------------------");
        System.out.print("Введите очередную команду:");
        return Command.parse(CommandLineInterface.scanner.nextLine());
    }
}
