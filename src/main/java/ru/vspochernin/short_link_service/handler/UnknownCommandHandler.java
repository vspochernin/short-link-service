package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Component;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;

@Component
public class UnknownCommandHandler implements CommandHandler {

    @Override
    public void handle(List<String> arguments) {
        throw new ShortLinkServiceException("Неизвестная команда");
    }

    @Override
    public void validate(List<String> arguments) {
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.UNKNOWN;
    }
}
