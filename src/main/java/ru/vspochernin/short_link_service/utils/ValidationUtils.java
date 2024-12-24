package ru.vspochernin.short_link_service.utils;

import java.util.List;

import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;

public class ValidationUtils {

    public static void validateEmptyArguments(List<String> arguments) {
        if (!arguments.isEmpty()) {
            throw new ShortLinkServiceException("Команда не поддерживает аргументы");
        }
    }
}
