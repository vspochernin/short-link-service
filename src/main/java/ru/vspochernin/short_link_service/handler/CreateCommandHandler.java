package ru.vspochernin.short_link_service.handler;

import java.util.List;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.ConfigValues;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class CreateCommandHandler implements CommandHandler {

    private ConfigValues currentConfigValues;

    @Override
    public void handle(List<String> arguments) {
        System.out.println("TODO: создать ссылку");
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateIdentification();

        if (arguments.size() == 1) {
            ValidationUtils.validateLongUrlNotBlank(arguments.get(0));
            if (ShortLinkContext.configValues.isEmpty()) {
                throw new ShortLinkServiceException("Отсутствует конфигурационный файл");
            }
            currentConfigValues = ShortLinkContext.configValues.get();
        } else if (arguments.size() == 3) {
            ValidationUtils.validateLongUrlNotBlank(arguments.get(0));
            int maxClicks = ValidationUtils.validateParseMaxClicks(arguments.get(1));
            long expirationSeconds = ValidationUtils.validateParseExpirationSeconds(arguments.get(2));
            currentConfigValues = ConfigValues.mergeWithConfigFile(maxClicks, expirationSeconds);
        } else {
            throw new ShortLinkServiceException("Некорректное количество аргументов, ожидается 1 или 3");
        }
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.CREATE;
    }
}
