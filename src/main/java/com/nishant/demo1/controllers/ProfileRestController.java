package com.nishant.demo1.controllers;

import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.services.ProfileRestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProfileRestController {

    @Autowired
    private ProfileRestService service;

    
    @GetMapping(value = "/profiles")
    public ResponseEntity<Object> getAllProfiles() {
        return service.getAllProfiles();
    }
    
    @GetMapping(value = "/profiles/{id}")
    public ResponseEntity<Object> getProfileById(@PathVariable long id) {
        return service.getProfileById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/profiles")
    public ResponseEntity<Object> createProfile(@RequestBody Profile profile) {
        return service.createProfile(profile);
    }

    @PreAuthorize("hasAnyRole('ADMIN','MOD')")
    @PutMapping(value = "profiles/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable long id, @RequestBody Profile profile) {
        return service.updateProfile(profile, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/profiles/{id}")
    public ResponseEntity<Object> deleteProfileById(@PathVariable long id) {
        return service.deleteProfileById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/profiles")
    public void deleteAll() {
        service.deleteAll();
    }
}
