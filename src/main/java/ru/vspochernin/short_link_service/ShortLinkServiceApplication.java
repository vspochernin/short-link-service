package ru.vspochernin.short_link_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortLinkServiceApplication implements CommandLineRunner {

    @Autowired
    private CommandLineInterface commandLineInterface;

    public static void main(String[] args) {
        SpringApplication.run(ShortLinkServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        commandLineInterface.run();
    }
}
