package com.nishant.demo1.services;

import com.nishant.demo1.SecurityConfiguration.MyUserDetails;
import com.nishant.demo1.entity.User;
import com.nishant.demo1.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userRepo.findByUsername(username);
        if(user==null) {  
            throw new UsernameNotFoundException("User not found with given username");
        }
        return new MyUserDetails(user);
    }
}
