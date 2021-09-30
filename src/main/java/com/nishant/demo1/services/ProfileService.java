package com.nishant.demo1.services;

import java.util.List;

import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepo;

    public List<Profile> getAllProfiles() {
        return profileRepo.findAll();
    }

    public Profile getProfileById(long id) {
        try {
            Profile profile = profileRepo.findById(id).orElseThrow();
            return profile;
        } catch (Exception e) {
            throw new RuntimeException("Profile with given ID not found");
        }
    }

    public Profile createProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    public Profile updateProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    public String deleteProfile(long id) {
        try {
            profileRepo.findById(id).orElseThrow();
        } catch (Exception e) {
            return "Profile not found with given ID";
        }
        profileRepo.deleteById(id);
        return "Profile "+id+" successfully deleted";
    }

}
