package com.dam.tweetspringdemo.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.HashMap;

public class LdapAuthenticationProvider implements AuthenticationProvider {
    private HashMap<String, String> userCredentials = new HashMap<>();

    public LdapAuthenticationProvider(){
        initUserCredentials();
    }

    private void initUserCredentials(){
        userCredentials.put("admin", "admin123");
        userCredentials.put("user", "user123");
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();
        if(userName!=null && password!=null){
            if(!password.equals(this.userCredentials.get(userName))){
                throw new BadCredentialsException("Username or password did not match");
            }
        }else{
            throw new BadCredentialsException(("Username or password did not match"));
        }
        Authentication token = new UsernamePasswordAuthenticationToken(userName, password);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
