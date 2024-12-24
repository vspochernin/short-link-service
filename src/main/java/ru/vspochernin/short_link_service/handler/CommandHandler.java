package ru.vspochernin.short_link_service.handler;

import java.util.List;

import ru.vspochernin.short_link_service.command.CommandType;

public interface CommandHandler {

    default void validateAndHandle(List<String> arguments) {
        validate(arguments);
        handle(arguments);
    }

    void handle(List<String> arguments);

    void validate(List<String> arguments);

    CommandType getHandlingCommandType();
}
