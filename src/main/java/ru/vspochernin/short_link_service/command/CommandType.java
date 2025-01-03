package ru.vspochernin.short_link_service.command;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CommandType {

    UNKNOWN(""),
    HELP("help"),
    CLICK("click"),
    REGISTER("register"),
    LOGIN("login"),
    CREATE("create"),
    LIST("list"),
    UPDATE("update"),
    DELETE("delete"),
    SET_CONFIG_FILE("scf"),
    EXIT("exit"),
    ;

    public static final Map<String, CommandType> byCommandTypeStrMap = Arrays.stream(values())
            .collect(Collectors.toMap(CommandType::getCommandTypeStr, Function.identity()));

    private final String commandTypeStr;

    public static CommandType parse(String commandTypeStr) {
        return Optional.ofNullable(byCommandTypeStrMap.get(commandTypeStr))
                .orElse(UNKNOWN);
    }
}
