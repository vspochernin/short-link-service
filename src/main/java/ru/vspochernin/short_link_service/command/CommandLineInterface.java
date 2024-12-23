package ru.vspochernin.short_link_service.command;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface {

    public static final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("""
                Чтобы использовать сервис, необходимо вводить команды:
                'exit' - для выхода""");

        Command command = Command.UNKNOWN;
        while (command != Command.EXIT) {
            command = Command.getNext();
            switch (command) {
                case UNKNOWN -> System.out.println("Введена неизвестная команда");
                case EXIT -> System.out.println("Сейчас будет выполнен выход");
                case REGISTER -> System.out.println("Сейчас будет произведена регистрация");
            }
        }
    }
}
