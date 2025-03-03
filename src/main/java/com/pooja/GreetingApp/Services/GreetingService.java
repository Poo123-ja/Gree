package com.pooja.GreetingApp.Services;

import com.pooja.GreetingApp.Repository.GreetingRepository;
import com.pooja.GreetingApp.model.GreetingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GreetingService {
    @Autowired
    private GreetingRepository greetingRepository;

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

    public GreetingModel saveGreeting(String firstName, String lastName) {
        String message;
        if (firstName != null && lastName != null) {
            message = "Hello, " + firstName + " " + lastName + "!";
        } else if (firstName != null) {
            message = "Hello, " + firstName + "!";
        } else if (lastName != null) {
            message = "Hello, " + lastName + "!";
        } else {
            message = "Hello World!";
        }


        GreetingModel greeting = new GreetingModel(message);
        return greetingRepository.save(greeting);
    }
    public GreetingModel getGreetById(Long id) {
        return greetingRepository.findById(id).orElseThrow(()->new RuntimeException("Greeting not found with id: " + id));
    }
    public List<GreetingModel> getAllGreetings() {
        return greetingRepository.findAll();
    }


}
