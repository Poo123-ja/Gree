package com.pooja.GreetingApp.Services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GreetingService {
    public String getSimpleGreeting() {
        return "Hello World!";
    }
}