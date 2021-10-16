package com.nishant.demo1.controllers;

import com.nishant.demo1.entity.User;
import com.nishant.demo1.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping(value="/register")
    public String viewRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        log.info("Registration Page Accessed");
        return "register";
    }

    @GetMapping(value="/signin")
    public String LoginPage() {
        log.info("Login Page Accessed");
        return "signin";
    }

    @PostMapping(value="/registerUser")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        user.setRole("ROLE_USER");
        model.addAttribute("user", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        log.info("Registration Successfull for user : "+user.getUsername());
        return "redirect:/register?success";
    }

    @GetMapping(value="/")
    public String redirectToHome() {
        return "redirect:/home";
    }

    @GetMapping(value="/temp")
    public String tempPage() {
        return "temp";
    }
}
