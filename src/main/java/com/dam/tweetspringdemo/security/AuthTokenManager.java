package com.dam.tweetspringdemo.security;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.TimeUnit;

@Configuration
public class AuthTokenManager {
    public static final int TOKEN_EXPIRATION_MINUTES = AuthTokenGeneratorImpl.TOKEN_EXP_MINUTES;
    private Cache<String, Authentication> tokenCache;

    @Autowired
    public AuthTokenManager(@Value("1000") int cacheSize) {
        initializeTokenCache(cacheSize);
    }

    private void initializeTokenCache(int cacheSize) {
        tokenCache = CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .expireAfterWrite(TOKEN_EXPIRATION_MINUTES, TimeUnit.MINUTES)
                .build();
    }

    private Authentication getAuthentication(){
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication();
    }

    private void setAuthentication(Authentication authentication){
        final SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    public void  saveToken(String token){
        final Authentication authentication = getAuthentication();
        tokenCache.put(token, authentication);
    }

    public boolean verifyToken(String token){
        final Authentication authentication = tokenCache.getIfPresent(token);
        if(authentication != null){
            setAuthentication(authentication);
        }
        return authentication != null;
    }
}
