package com.dam.tweetspringdemo.security;

import org.springframework.stereotype.Service;

@Service
public class AuthTokenGeneratorImpl implements AuthTokenGenerator {

//    Token expires after a day
    public static final int TOKEN_EXP_MINUTES = 24*60;

    @Override
    public String generateAuthToken() {

        return null;
    }
}
