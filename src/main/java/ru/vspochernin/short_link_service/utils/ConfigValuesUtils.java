package ru.vspochernin.short_link_service.utils;

import java.io.FileInputStream;
import java.util.Optional;
import java.util.Properties;

import ru.vspochernin.short_link_service.entity.ConfigValues;

public class ConfigValuesUtils {

    private static final String MAX_CLICKS_PROPERTY = "maxClicks";
    private static final String EXPIRATION_SECONDS_PROPERTY = "expirationSeconds";

    public static Optional<ConfigValues> getConfigValuesFromFile(Optional<String> configFilePathO) {
        if (configFilePathO.isEmpty()) {
            return Optional.empty();
        }
        String configFilePath = configFilePathO.get();

        try (FileInputStream fis = new FileInputStream(configFilePath)) {
            Properties properties = new Properties();
            properties.load(fis);

            return Optional.of(new ConfigValues(
                    Integer.parseInt(properties.getProperty(MAX_CLICKS_PROPERTY)),
                    Long.parseLong(properties.getProperty(EXPIRATION_SECONDS_PROPERTY))));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
