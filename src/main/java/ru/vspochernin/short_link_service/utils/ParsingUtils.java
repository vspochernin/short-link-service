package ru.vspochernin.short_link_service.utils;

import ru.vspochernin.short_link_service.exception.ShortLinkServiceException;

public class ParsingUtils {

    public static int parseMaxClicks(String maxClicksStr) {
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

    public static long parseExpirationSeconds(String expirationSecondsStr) {
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

    public static int parseLinkId(String linkIdStr) {
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
