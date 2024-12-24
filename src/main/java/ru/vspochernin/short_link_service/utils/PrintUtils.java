package ru.vspochernin.short_link_service.utils;

public class PrintUtils {

    public static void printHelpMessage() {
        System.out.println("""
                Сервис коротких ссылок поддерживает следующие команды:
                'help' - вывод данного сообщения,
                'register' - регистрация нового uuid,
                'login [uuid]' - идентификация пользователя,
                'exit' - выход из программы""");
    }
}
