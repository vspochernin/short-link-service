package ru.vspochernin.short_link_service.utils;

public class DebugLoggerUtils {

    private static final boolean ENABLED = false;

    public static void logIfDebugEnabled(String message) {
        if (ENABLED) {
            System.out.println("DEBUG: " + message);
        }
    }
}
