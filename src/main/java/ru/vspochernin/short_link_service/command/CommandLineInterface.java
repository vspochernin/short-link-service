package ru.vspochernin.short_link_service.command;

import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface {

    public void run() {
        System.out.println("""
                Чтобы использовать сервис, необходимо вводить команды:
                'exit' - для выхода""");

        Command command = Command.getUnknown();
        while (command.commandType() != CommandType.EXIT) {
            command = Command.getNext();
            switch (command.commandType()) {
                case UNKNOWN -> System.out.println("Введена неизвестная команда");
                case EXIT -> System.out.println("Сейчас будет выполнен выход");
                case REGISTER -> System.out.println("Сейчас будет произведена регистрация");
            }
        }
    }
}
