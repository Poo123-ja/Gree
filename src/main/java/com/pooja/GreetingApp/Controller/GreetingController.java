
package com.pooja.GreetingApp.Controller;

import com.pooja.GreetingApp.Services.GreetingService;
import com.pooja.GreetingApp.custumExceptions.ResourceNotFoundException;
import com.pooja.GreetingApp.model.GreetingModel;
import com.pooja.GreetingApp.Repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private GreetingService greetingService;
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
    @Autowired
    private GreetingService simpleGreet;
    @GetMapping("/simple")
    public String getSimpleGreeting(){
        return simpleGreet.getSimpleGreeting();
    }
    @GetMapping("/message")
    public String getGreetingMessage(@RequestParam(required = false) String firstName,
                                     @RequestParam(required = false) String lastName){
        return greetingService.getGreeting(firstName, lastName);

    }
    @PostMapping("/save")
    public GreetingModel saveGreeting(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {
        return greetingService.saveGreeting(firstName, lastName);
    }
    @GetMapping("getId/{id}")
    public GreetingModel getGreetingById(@PathVariable Long id){
        return greetingService.getGreetById(id);
    }
    @GetMapping("/getAll")
    public List<GreetingModel> getAllGreetings(){
        return greetingService.getAllGreetings();
    }
    @PutMapping("/updateRepo/{id}")
    public GreetingModel updateGreetingRepo(@PathVariable Long id, @RequestBody GreetingModel greetingDetails){
        return greetingService.updateGreeting(id, greetingDetails.getMessage());
    }
    @DeleteMapping("/deleteFromRepo/{id}")
    public ResponseEntity<String> deleteGreetingRepo(@PathVariable Long id){
        try {
            greetingService.deleteGreeting(id);
            return ResponseEntity.ok("Greeting deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
