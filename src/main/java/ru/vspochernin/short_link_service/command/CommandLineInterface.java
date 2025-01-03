package ru.vspochernin.short_link_service.command;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.handler.CommandHandler;
import ru.vspochernin.short_link_service.utils.PrintUtils;

@Service
public class CommandLineInterface {

    private final Map<CommandType, CommandHandler> commandHandlersMap;

    public CommandLineInterface(List<CommandHandler> commandHandlers) {
        this.commandHandlersMap = commandHandlers.stream()
                .collect(Collectors.toMap(
                        CommandHandler::getHandlingCommandType,
                        Function.identity(),
                        (a, b) -> a,
                        () -> new EnumMap<>(CommandType.class)));
    }

    public void run() {
        PrintUtils.printHelpMessage();

        Command currentCommand;
        while (ShortLinkContext.isRunning) {
            currentCommand = Command.getNext();
            try {
                Optional.ofNullable(commandHandlersMap.get(currentCommand.commandType()))
                        .orElse(commandHandlersMap.get(CommandType.UNKNOWN))
                        .validateAndHandle(currentCommand.arguments());
            } catch (ShortLinkServiceException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }
}
