package ru.vspochernin.short_link_service.context;

import java.util.Optional;

import ru.vspochernin.short_link_service.entity.ConfigValues;
import ru.vspochernin.short_link_service.entity.User;

public class ShortLinkContext {

    public static boolean isRunning = true;
    public static Optional<User> currentUser = Optional.empty();
    public static Optional<ConfigValues> configFileValues = Optional.empty();
}
