package com.nishant.demo1.services;

import com.nishant.demo1.entity.User;
import com.nishant.demo1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Boolean userAlreadyExists(String curUsername) {
        if(userRepo.findByUsername(curUsername)!=null)
            return true;
        return false;
    }
}
