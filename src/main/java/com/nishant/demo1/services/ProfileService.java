package com.nishant.demo1.services;

import java.util.List;

import com.nishant.demo1.entity.Address;
import com.nishant.demo1.entity.Profile;
import com.nishant.demo1.repository.ProfileRepository;
import com.nishant.demo1.utils.Utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepo;

    public ResponseEntity<Object> getAllProfiles() {
        List<Profile> listProfile = profileRepo.findAll();
        if (listProfile.size() == 0) {
            return new ResponseEntity<>("No profile found",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(profileRepo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Object> getProfileById(long id) {
        try {
            Profile profile = profileRepo.findById(id).orElseThrow();
            return new ResponseEntity<>(profile, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Profile with given ID not found",HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> createProfile(Profile profile) {
        if (profile.getFirstName() == null              || 
            profile.getPhoneNumber() == null            || 
            profile.getAddress().getAddLine1() == null  || 
            profile.getAddress().getCity() == null      || 
            profile.getAddress().getPincode() == null   || 
            profile.getAddress().getCountry() == null
        ) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Enter All Mandatory Fields");
        }
        if (!Utils.isValidPhoneNumber(profile.getPhoneNumber())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Phone number is Invalid");
        }
        if (!Utils.isValidPincode(profile.getAddress().getPincode())) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Pincode is Invalid");
        }
        return new ResponseEntity<>(profileRepo.save(profile), HttpStatus.CREATED);
    }

    public ResponseEntity<Object> updateProfile(Profile profile, long id) {
        Profile extractedProfile;
        try {
            extractedProfile = profileRepo.findById(id).orElseThrow();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile with ID " + id + " not found");
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
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Phone Number is Invalid");
            }
        }
        Address address = profile.getAddress();
        if (address != null) {
            if (address.getAddLine1() == null || 
                address.getCity() == null     || 
                address.getCountry() == null  || 
                address.getPincode() == null
                ) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Enter All mandatory fields");
            }
            if (!Utils.isValidPincode(address.getPincode())) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Pincode is Invalid");
            }
            // extractedProfile.setAddress(address);
            extractedProfile.getAddress().setAddLine1(address.getAddLine1());
            extractedProfile.getAddress().setAddLine2(address.getAddLine2());
            extractedProfile.getAddress().setCity(address.getCity());
            extractedProfile.getAddress().setCountry(address.getCountry());
            extractedProfile.getAddress().setPincode(address.getPincode());
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileRepo.save(extractedProfile));
    }

    public ResponseEntity<String> deleteProfileById(long id) {
        Boolean exists = profileRepo.existsById(id);
        if (!exists)
            return new ResponseEntity<>("Profile with id " + id + " not found", HttpStatus.NOT_FOUND);
        profileRepo.deleteById(id);
        return new ResponseEntity<>("Profile with id " + id + " successfully deleted", HttpStatus.resolve(202));
    }

    public void deleteAll() {
        profileRepo.deleteAll();
    }

}
