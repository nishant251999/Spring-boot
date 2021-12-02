package com.nishant.demo1.controllers;

import com.nishant.demo1.entity.User;
import com.nishant.demo1.model.JWTRequest;
import com.nishant.demo1.model.JWTResponse;
import com.nishant.demo1.services.MyUserDetailsService;
import com.nishant.demo1.services.UserService;
import com.nishant.demo1.utils.JwtUtility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtility;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private UserService userService;
    
    @PostMapping(value="/generate-token")
    public ResponseEntity<Object> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),jwtRequest.getPassword()) );
        } catch (UsernameNotFoundException e) {
            log.error("Bad credentials entered");
            throw new Exception("Bad Credentials");
        }

        //username pw are legit

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtility.generateToken(userDetails);
        System.out.println("JWT Token "+token);

        return ResponseEntity.ok(new JWTResponse(token));
    }

    @PostMapping(value="/create-user")  
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        if(userService.userAlreadyExists(user.getUsername())) {
            log.error("Username Already Exists");
            return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(userService.createUser(user));
    }
}
