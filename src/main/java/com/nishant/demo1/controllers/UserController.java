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



@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @GetMapping(value="/register")
    public String viewRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @GetMapping(value="/signin")
    public String LoginPage() {
        return "signin";
    }
    

    @PostMapping(value="/registerUser")
    public String registerUser(@ModelAttribute("user") User user, Model model) {

        user.setRole("ROLE_USER");
        model.addAttribute("user", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return "redirect:/register?success";
    }
    
}
