package com.sticky.notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sticky.notes.controller, com.sticky.notes.model")
public class StickyNotesApplication {
    public static void main(String[] args) {
        SpringApplication.run(StickyNotesApplication.class, args);
    }
}