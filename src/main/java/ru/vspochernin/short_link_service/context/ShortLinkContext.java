package ru.vspochernin.short_link_service.context;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import ru.vspochernin.short_link_service.command.Command;
import ru.vspochernin.short_link_service.command.CommandType;
import ru.vspochernin.short_link_service.entity.ConfigValues;

public class ShortLinkContext {

    public static Command currentCommand = new Command(CommandType.UNKNOWN, List.of());
    public static boolean isRunning = true;
    public static Optional<UUID> currentUser = Optional.empty();
    public static Optional<ConfigValues> configValues = Optional.empty();
}
