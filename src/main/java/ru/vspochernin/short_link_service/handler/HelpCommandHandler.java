package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.utils.PrintUtils;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class HelpCommandHandler implements CommandHandler {

    @Override
    public void handle(List<String> arguments) {
        PrintUtils.printHelpMessage();
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateEmptyArguments(arguments);
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.HELP;
    }
}
