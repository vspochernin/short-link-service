package ru.vspochernin.short_link_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShortLinkServiceApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ShortLinkServiceApplication.class, args);
        System.out.println("Hello World!");
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello World!" + i);
            Thread.sleep(300);
        }
    }
}
