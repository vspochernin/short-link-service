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

    public static int validateParseMaxClicks(String maxClicksStr) {
        if (maxClicksStr == null || maxClicksStr.isBlank()) {
            throw new ShortLinkServiceException("Максимальное число переходов не должно быть пустым");
        }

        try {
            int maxClicks = Integer.parseInt(maxClicksStr);
            if (maxClicks <= 0) {
                throw new RuntimeException();
            }
            return maxClicks;
        } catch (Exception e) {
            throw new ShortLinkServiceException("Некорректное максимальное число переходов");
        }
    }

    public static long validateParseExpirationSeconds(String expirationSecondsStr) {
        if (expirationSecondsStr == null || expirationSecondsStr.isBlank()) {
            throw new ShortLinkServiceException("Время жизни ссылки в секундах не должно быть пустым");
        }

        try {
            long expirationSeconds = Long.parseLong(expirationSecondsStr);
            if (expirationSeconds <= 0) {
                throw new RuntimeException();
            }
            return expirationSeconds;
        } catch (Exception e) {
            throw new ShortLinkServiceException("Некорректное время жизни ссылки в секундах");
        }
    }

    public static int validateParseLinkId(String linkIdStr) {
        if (linkIdStr == null || linkIdStr.isBlank()) {
            throw new ShortLinkServiceException("id ссылки не должен быть пустым");
        }

        try {
            int linkId = Integer.parseInt(linkIdStr);
            if (linkId <= 0) {
                throw new RuntimeException();
            }
            return linkId;
        } catch (Exception e) {
            throw new ShortLinkServiceException("Некорректный id ссылки");
        }
    }
}
