package com.pooja.GreetingApp.Controller;


import com.pooja.GreetingApp.Services.EmailService;
import com.pooja.GreetingApp.Services.UserService;
import com.pooja.GreetingApp.dto.LoginDTO;
import com.pooja.GreetingApp.dto.RegisterDTO;
import com.pooja.GreetingApp.model.User;
import com.pooja.GreetingApp.utility.JwtUtility;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtility jwtUtility;
    @Autowired
    EmailService emailService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody RegisterDTO registerUser) {
        if (userService.existsByEmail(registerUser.getEmail())) {
            return "User Already Exists";
        }

        User user = new User();
        user.setFullName(registerUser.getFullName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(registerUser.getPassword());

        User savedUser = userService.registerUser(user);
        String token = jwtUtility.generateToken(savedUser.getEmail());
        String subject = "Welcome to Our Platform!";
        String body = "Hello " + savedUser.getFullName() + ",\n\nYour account has been successfully created!";
        emailService.sendEmail(savedUser.getEmail(), subject, body);
        return "User Registered Successfully and Your Token is: " + token;
    }

    @PostMapping("/login")
    public String loginUser(@Valid@RequestBody LoginDTO loginUser){
        Optional<User> userExists = userService.getUserByEmail(loginUser.getEmail());
        if(userExists.isPresent()){
            User user = userExists.get();
            if(userService.matchPassword(loginUser.getPassword(),user.getPassword())){
                String token = jwtUtility.generateToken(user.getEmail());
                String subject = "Welcome Back to Our Platform!";
                String body = "Hello " + user.getFullName() + ",\n\nYour account has been successfully Logged In! and Your Token is: "+token;
                emailService.sendEmail(user.getEmail(), subject, body);
                return "User Login Successfully and Token is: "+token;
            }
            else{
                return "InValid Crendentials";
            }
        }
        else{
            return "User Not Found";
        }
    }

}
