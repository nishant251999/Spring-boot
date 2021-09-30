package com.nishant.demo1.controllers;

import java.util.List;

import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.services.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping(value = "/profiles")
    public List<Profile> getAllProfiles() {
        return service.getAllProfiles();
    }

    @GetMapping(value = "/profiles/{id}")
    public Profile getProfileById(@PathVariable long id) {
        return service.getProfileById(id);
    }

    @PostMapping(value = "/profiles")
    public Profile createProfile(@RequestBody Profile profile) {
        return service.createProfile(profile);
    }

    @PutMapping(value = "profiles/{id}")
    public Profile updateProfile(@PathVariable long id, @RequestBody Profile profile) {
        return service.updateProfile(profile, id);
    }

    @DeleteMapping(value = "/profiles/{id}")
    public String deleteProfile(@PathVariable long id) {
        return service.deleteProfile(id);
    }
}
