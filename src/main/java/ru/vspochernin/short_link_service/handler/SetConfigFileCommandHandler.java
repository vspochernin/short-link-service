package ru.vspochernin.short_link_service.handler;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.entity.ConfigValues;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;
import ru.vspochernin.short_link_service.utils.ConfigValuesUtils;
import ru.vspochernin.short_link_service.utils.ValidationUtils;

@Service
public class SetConfigFileCommandHandler implements CommandHandler {

    @Override
    public void handle(List<String> arguments) {
        Optional<String> configFilePath = Optional.of(arguments.get(0));
        Optional<ConfigValues> configValues = ConfigValuesUtils.getConfigValuesFromFile(configFilePath);

        if (configValues.isEmpty()) {
            throw new ShortLinkServiceException("Конфигурационный файл отсутствует или составлен некорректно");
        }

        ShortLinkContext.configFileValues = configValues;
        System.out.println("Установлена следующая конфигурация: " + configValues.get());
    }

    @Override
    public void validate(List<String> arguments) {
        ValidationUtils.validateArgumentsCount(arguments, 1);
    }

    @Override
    public CommandType getHandlingCommandType() {
        return CommandType.SET_CONFIG_FILE;
    }
}
