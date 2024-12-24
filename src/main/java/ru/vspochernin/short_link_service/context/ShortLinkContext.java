package ru.vspochernin.short_link_service.context;

import java.util.List;

import ru.vspochernin.short_link_service.command.Command;
import ru.vspochernin.short_link_service.command.CommandType;

public class ShortLinkContext {

    public static Command currentCommand = new Command(CommandType.UNKNOWN, List.of());
    public static boolean isRunning = true;
}
