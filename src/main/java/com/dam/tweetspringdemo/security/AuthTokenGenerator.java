package com.dam.tweetspringdemo.security;

import org.springframework.stereotype.Service;

@Service
public interface AuthTokenGenerator {
    public String generateAuthToken();
}
