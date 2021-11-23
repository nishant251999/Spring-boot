package com.nishant.demo1.services;

import com.nishant.demo1.entity.User;
import com.nishant.demo1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User createUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        return userRepo.save(user);
    }

    public Boolean userAlreadyExists(String curUsername) {
        if(userRepo.findByUsername(curUsername)!=null)
            return true;
        return false;
    }
}
