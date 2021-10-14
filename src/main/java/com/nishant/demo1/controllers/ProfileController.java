package com.nishant.demo1.controllers;

import java.util.List;

import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping(value="/home")
    public String viewHome(Model model) {
        List<Profile> profileList = service.getAllProfiles();
        model.addAttribute("profiles", profileList);
        return "home";
    }
    
    @GetMapping(value="/addProfileForm")
    public String showProfileForm(Model model) {
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        return "addProfileForm";
    }
    
    @PostMapping(value="/createProfile")
    public String createProfile(@ModelAttribute("profile") Profile profile) {
        service.createProfile(profile);
        return "redirect:/home";
    }

    @GetMapping(value="/updateProfileForm/{id}")
    public String updateProfileForm(@PathVariable long id, Model model) {
        Profile profile = service.getProfileById(id);
        model.addAttribute("profile", profile);
        return "updateProfileForm";
    }
    @PostMapping(value="/updateProfile/{id}")
    public String createProfile(@ModelAttribute("profile") Profile profile, @PathVariable long id) {
        service.updateProfile(profile, id);
        return "redirect:/home";
    }

    @GetMapping(value = "/deleteProfile/{id}")
    public String deleteProfileById(@PathVariable long id) {
        service.deleteProfileById(id);
        return "redirect:/home";
    }

    @GetMapping(value="/")
    public String redirectToHome() {
        return "redirect:/home";
    }
    
}
