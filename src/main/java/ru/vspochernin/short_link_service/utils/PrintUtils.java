package ru.vspochernin.short_link_service.utils;

public class PrintUtils {

    public static void printHelpMessage() {
        System.out.println("""
                ----------
                Сервис коротких ссылок поддерживает следующие команды:
                -----
                'help' - вывод данного сообщения,
                'click' [короткая ссылка] - перейти по короткой ссылке,
                'register' - регистрация нового uuid,
                'login [uuid]' - идентификация пользователя,
                'create [длинная ссылка] - создать короткую ссылку (должен быть установлен конфигурационный файл),
                'create [длинная ссылка] [максимальное число переходов] [время жизни в секунда] - создать короткую ссылку,
                'list' - вывести список своих ссылок,
                'update' [id ссылки] [новое максимальное число переходов] - изменить максимальное число переходов по ссылке,
                'delete' [id ссылки] - удалить ссылку,
                'scf [абсолютный путь до конфигурационного файла] - установка конфигурационного файла,
                'exit' - выход из программы
                -----
                Конфигурационный файл при наличии должен содержать структуру, подобную следующей:
                maxClicks=3
                expirationSeconds=60
                -----
                - Максимальное число переходов по ссылке должно быть положительным целым числом (в пределах int).
                - Время жизни ссылки в секундах должно быть положительным целым числом (в пределах long).
                - id ссылки должен быть положительным целым числом (в пределах int).
                ----------""");
    }
}
