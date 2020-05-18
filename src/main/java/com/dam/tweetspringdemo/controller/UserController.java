package com.dam.tweetspringdemo.controller;

import com.dam.tweetspringdemo.security.AuthTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @@author darsh_mehta@yahoo.com
 * User Controller, used to generate authorization token on successful login
 */
@RestController
public class UserController {
    @Autowired
    AuthTokenGenerator authTokenGenerator;

    @RequestMapping(value = "/user/login", produces = "application/json")
    public ResponseEntity<String> generateAuthToken(){
        return new ResponseEntity<String>(authTokenGenerator.generateAuthToken(), HttpStatus.OK);
    }
}
