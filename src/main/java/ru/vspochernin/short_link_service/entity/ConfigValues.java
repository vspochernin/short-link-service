package ru.vspochernin.short_link_service.entity;

import ru.vspochernin.short_link_service.context.ShortLinkContext;

public record ConfigValues(
        int maxClicks,
        long expirationSeconds)
{

    public static ConfigValues mergeWithConfigFile(int maxClicks, long expirationSeconds) {
        if (ShortLinkContext.configFileValues.isEmpty()) {
            return new ConfigValues(maxClicks, expirationSeconds);
        }

        ConfigValues configValuesFromFile = ShortLinkContext.configFileValues.get();
        int maxClicksFromFile = configValuesFromFile.maxClicks;
        long expirationSecondsFromFile = configValuesFromFile.expirationSeconds;

        return new ConfigValues(
                Math.max(maxClicks, maxClicksFromFile),
                Math.min(expirationSecondsFromFile, expirationSeconds));
    }
}
