package com.pooja.GreetingApp.Services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String getSimpleGreeting() {
        return "Hello World!";
    }
    public String getGreeting(String firstName, String lastName) {
        if(firstName != null && lastName != null) {
            return "Hello " + firstName + " " + lastName;
        } else if (firstName != null) {
            return "Hello " + firstName;
        }
        else if (lastName != null) {
            return "Hello " + lastName;
        }
        else {
            return "Hello World!";
        }
    }
}