package ru.vspochernin.short_link_service.log;

public class DebugLogger {

    private static final boolean ENABLED = true;

    public static void log(String message) {
        if (ENABLED) {
            System.out.println("DEBUG: " + message);
        }
    }
}
