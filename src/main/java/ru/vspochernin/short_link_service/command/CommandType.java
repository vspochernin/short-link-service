package ru.vspochernin.short_link_service.command;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CommandType {

    UNKNOWN(""),
    EXIT("exit"),
    REGISTER("register"),
    ;

    public static final Map<String, CommandType> byCommandTypeStrMap = Arrays.stream(values())
            .collect(Collectors.toMap(CommandType::getCommandTypeStr, Function.identity()));

    private final String commandTypeStr;

    CommandType(String commandTypeStr) {
        this.commandTypeStr = commandTypeStr;
    }

    public String getCommandTypeStr() {
        return commandTypeStr;
    }

    public static CommandType parse(String commandTypeStr) {
        return Optional.ofNullable(byCommandTypeStrMap.get(commandTypeStr))
                .orElse(UNKNOWN);
    }
}
