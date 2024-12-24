package ru.vspochernin.short_link_service.command;

import java.util.regex.Pattern;

public enum CommandType {

    UNKNOWN("^$"),
    EXIT("^exit$"),
    REGISTER("^register$"),
    ;

    private final String regex;

    CommandType(String regex) {
        this.regex = regex;
    }

    public static CommandType parse(String commandStr) {
        for (CommandType commandType : values()) {
            if (Pattern.compile(commandType.regex).matcher(commandStr).find()) {
                return commandType;
            }
        }
        return UNKNOWN;
    }
}
