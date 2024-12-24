package ru.vspochernin.short_link_service.command;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.handler.CommandHandler;

@Component
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
        printStartMessage();
        while (ShortLinkContext.isRunning) {
            ShortLinkContext.currentCommand = Command.getNext();
            try {
                Optional.ofNullable(commandHandlersMap.get(ShortLinkContext.currentCommand.commandType()))
                        .orElse(commandHandlersMap.get(CommandType.UNKNOWN))
                        .validateAndHandle(ShortLinkContext.currentCommand.arguments());
            } catch (ShortLinkServiceException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static void printStartMessage() {
        System.out.println("""
                Добрый день! Сервис коротких ссылок поддерживает следующие команды:
                'register' - регистрация нового uuid,
                'exit' - выход из программы""");

    }
}
