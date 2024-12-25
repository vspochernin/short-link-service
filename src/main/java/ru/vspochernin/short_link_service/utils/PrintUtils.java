package ru.vspochernin.short_link_service.utils;

public class PrintUtils {

    public static void printHelpMessage() {
        System.out.println("""
                Сервис коротких ссылок поддерживает следующие команды:
                'help' - вывод данного сообщения,
                'register' - регистрация нового uuid,
                'login [uuid]' - идентификация пользователя,
                'scf [абсолютный путь до конфигурационного файла] - установка конфигурационного файла,
                'exit' - выход из программы
                
                Конфигурационный файл при наличии должен содержать структуру, подобную следующей:
                maxclicks=[int]
                expirationseconds=[long]
                Например:
                maxclicks=3
                expirationseconds=60""");
    }
}
