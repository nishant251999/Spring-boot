package com.nishant.demo1.controllers;

import java.security.Principal;
import java.util.List;

import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.entity.User;
import com.nishant.demo1.services.ProfileRestService;
import com.nishant.demo1.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private ProfileRestService service;

    @GetMapping(value="/home")
    public String viewHome(Model model, Authentication auth) {
        String curUser = auth.getName();
        String curUserRole = auth.getAuthorities().toString();
        curUserRole = curUserRole.substring(6,curUserRole.length()-1);
        List<Profile> profileList = service.getAllProfilesForView();

        model.addAttribute("profiles", profileList);
        model.addAttribute("curUser",curUser);
        model.addAttribute("curUserRole",curUserRole);

        log.info("Homepage accessed by user : "+curUser);
        return "home";
    }
    
    @GetMapping(value="/addProfileForm")
    public String showProfileForm(Model model, Authentication auth) {
        String curUser = auth.getName();
        Profile profile = new Profile();
        model.addAttribute("profile", profile);
        log.info("Add Form accessed by user : "+curUser);
        return "addProfileForm";
    }
    
    @PostMapping(value="/createProfile")
    public String createProfile(@ModelAttribute("profile") Profile profile, Authentication auth) {
        String curUser = auth.getName();
        ResponseEntity<Object> response = service.createProfile(profile);

        log.info("Profile Added by user : "+curUser);
        return "redirect:/home";
    }

    @GetMapping(value="/updateProfileForm/{id}")
    public String updateProfileForm(@PathVariable long id, Model model, Authentication auth) {
        String curUser = auth.getName();
        Profile profile = service.getProfileByIdForView(id);
        log.info("Update Form Accessed by user : "+curUser);
        model.addAttribute("profile", profile);
        return "updateProfileForm";
    }
    @PostMapping(value="/updateProfile/{id}")
    public String createProfile(@ModelAttribute("profile") Profile profile, @PathVariable long id, Principal principal) {
        String curUser = principal.getName();
        service.updateProfile(profile, id);
        log.info("Profile ID:"+profile.getId()+" updated by user : "+curUser);
        return "redirect:/home";
    }

    @GetMapping(value = "/deleteProfile/{id}")
    public String deleteProfileById(@PathVariable long id, Authentication auth) {
        String curUser = auth.getName();
        service.deleteProfileById(id);
        log.info("Profile ID : "+id+" deleted by user : "+curUser);
        return "redirect:/home";
    }
    
}
