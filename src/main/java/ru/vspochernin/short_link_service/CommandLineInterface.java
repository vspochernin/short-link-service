package ru.vspochernin.short_link_service;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class CommandLineInterface {

    private static final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("""
                Чтобы использовать сервис, необходимо вводить команды:
                'exit' - для выхода""");

        String command = "unknown";
        while (!command.equals("exit")) {
            command = scanner.nextLine();
            switch (command) {
                default -> System.out.println("Command: " + command);
            }
        }
    }
}
