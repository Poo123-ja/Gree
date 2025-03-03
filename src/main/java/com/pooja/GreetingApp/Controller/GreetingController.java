
package com.pooja.GreetingApp.Controller;

import com.pooja.GreetingApp.custumExceptions.ResourceNotFoundException;
import com.pooja.GreetingApp.model.GreetingModel;
import com.pooja.GreetingApp.Repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingRepository greetingRepository;

    @GetMapping
    public List<GreetingModel> getGreetings(){
        return greetingRepository.findAll();
    }

    @PostMapping
    public GreetingModel createGreeting(@RequestBody GreetingModel greeting){
        logger.info("Received Greeting: " + greeting);
        return greetingRepository.save(greeting);
    }

    @PutMapping("/{id}")
    public GreetingModel updateGreeting(@PathVariable Long id, @RequestBody GreetingModel greetingDetails){
        logger.info("Updating Greeting with ID: " + id);
        GreetingModel greeting = greetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Greeting not found with id " + id));
        greeting.setMessage(greetingDetails.getMessage());
        return greetingRepository.save(greeting);
    }

    @DeleteMapping("/{id}")
    public void deleteGreeting(@PathVariable Long id){
        greetingRepository.deleteById(id);
    }
}
