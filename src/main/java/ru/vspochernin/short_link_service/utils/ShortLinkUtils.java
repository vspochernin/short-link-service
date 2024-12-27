package ru.vspochernin.short_link_service.utils;

import java.util.Random;

public class ShortLinkUtils {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String BASE_PART = "click.ru/";
    private static final int MAIN_PART_LENGTH = 6;

    public static String generateShortUrl() {
        Random random = new Random();

        StringBuilder shortUrlBuilder = new StringBuilder().append(BASE_PART);
        for (int i = 0; i < MAIN_PART_LENGTH; i++) {
            shortUrlBuilder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }

        return shortUrlBuilder.toString();
    }
}
