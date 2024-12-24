package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Component;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Component
public class ExitCommandHandler implements CommandHandler {

    @Override
    public void handle(List<String> arguments) {
        System.out.println("Будет выполнен выход из программы");
        ShortLinkContext.isRunning = false;
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateEmptyArguments(arguments);
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.EXIT;
    }
}
