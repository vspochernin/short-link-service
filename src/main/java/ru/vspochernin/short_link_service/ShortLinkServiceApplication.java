package ru.vspochernin.short_link_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.vspochernin.short_link_service.command.CommandLineInterface;

@SpringBootApplication
public class ShortLinkServiceApplication implements CommandLineRunner {

    private final CommandLineInterface commandLineInterface;

    public ShortLinkServiceApplication(CommandLineInterface commandLineInterface) {
        this.commandLineInterface = commandLineInterface;
    }

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        commandLineInterface.run();
    }
}
