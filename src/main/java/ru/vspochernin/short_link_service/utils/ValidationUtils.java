package ru.vspochernin.short_link_service.utils;

import java.util.List;

import ru.vspochernin.short_link_service.context.ShortLinkContext;
import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;

public class ValidationUtils {

    public static void validateEmptyArguments(List<String> arguments) {
        if (!arguments.isEmpty()) {
            throw new ShortLinkServiceException("Команда не поддерживает аргументы");
        }
    }

    public static void validateArgumentsCount(List<String> arguments, int count) {
        if (arguments.size() != count) {
            throw new ShortLinkServiceException("Некорректное количество аргументов, ожидается: " + count);
        }
    }

    public static void validateIdentification() {
        if (ShortLinkContext.currentUser.isEmpty()) {
            throw new ShortLinkServiceException("Пользователь не идентифицирован");
        }
    }

    public static void validateLongUrlNotBlank(String longUrl) {
        if (longUrl.isBlank()) {
            throw new ShortLinkServiceException("Длинная ссылка не должна быть пустой");
        }
    }

    public static void validateShortUrlNotBlank(String shortUtl) {
        if (shortUtl.isBlank()) {
            throw new ShortLinkServiceException("Короткая ссылка не должна быть пустой");
        }
    }
}
