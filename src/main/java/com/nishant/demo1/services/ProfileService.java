package com.nishant.demo1.services;

import java.util.List;

import com.nishant.demo1.entity.Address;
import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.repository.ProfileRepository;
import com.nishant.demo1.utils.Utils;

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
        if( profile.getFirstName()==null            ||
            profile.getPhoneNumber()==null          ||
            profile.getAddress().getAddLine1()==null||
            profile.getAddress().getCity()==null    ||
            profile.getAddress().getPincode()==null ||
            profile.getAddress().getCountry()==null
        ) {
            throw new RuntimeException("Enter all madatory fields to create profile");
        } 
        if(!Utils.isValidPhoneNumber(profile.getPhoneNumber()))
            throw new RuntimeException("Enter a valid Phone Number");

        if(!Utils.isValidPincode(profile.getAddress().getPincode()))
            throw new RuntimeException("Enter a valid Pincode");
        return profileRepo.save(profile);
    }

    public Profile updateProfile(Profile profile, long id) {
        Profile extractedProfile;
        try {
            extractedProfile = profileRepo.findById(id).orElseThrow();
        } catch (Exception e) {
            throw new RuntimeException("Profile not found with given ID");
        }

        String firstName = profile.getFirstName();
        if (firstName != null)
            extractedProfile.setFirstName(firstName);

        String lastName = profile.getLastName();
        if (lastName != null)
            extractedProfile.setLastName(lastName);

        String phoneNumber = profile.getPhoneNumber();
        if (phoneNumber != null) {
            if (Utils.isValidPhoneNumber(phoneNumber)) {
                extractedProfile.setPhoneNumber(phoneNumber);
            } else {
                throw new RuntimeException("Phone Number not valid");
            }
        }
        Address address = profile.getAddress();
        if (address != null) {
            if( address.getAddLine1()==null ||
                address.getCity()==null     ||
                address.getCountry()==null  ||
                address.getPincode()==null  
            ) {
                throw new RuntimeException("Enter all required fields");
            }
            if(!Utils.isValidPincode(address.getPincode())) {
                throw new RuntimeException("Enter a valid Pincode");
            }
            // extractedProfile.setAddress(address);
            extractedProfile.getAddress().setAddLine1(address.getAddLine1());
            extractedProfile.getAddress().setAddLine2(address.getAddLine2());
            extractedProfile.getAddress().setCity(address.getCity());
            extractedProfile.getAddress().setCountry(address.getCountry());
            extractedProfile.getAddress().setPincode(address.getPincode());
        }
        return profileRepo.save(extractedProfile);
    }

    public String deleteProfile(long id) {
        try {
            profileRepo.findById(id).orElseThrow();
        } catch (Exception e) {
            return "Profile not found with given ID";
        }
        profileRepo.deleteById(id);
        return "Profile " + id + " successfully deleted";
    }

}
