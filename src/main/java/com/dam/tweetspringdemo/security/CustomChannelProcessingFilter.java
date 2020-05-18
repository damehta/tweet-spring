package com.dam.tweetspringdemo.security;

import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CustomChannelProcessingFilter extends ChannelProcessingFilter {

    private final AuthTokenManager authTokenManager;

    public CustomChannelProcessingFilter(AuthTokenManager authTokenManager) {
        this.authTokenManager = authTokenManager;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if(request.getServletPath().equals(WebSecurityConfig.USER_LOGIN_PATH)){
            chain.doFilter(req, res);
        }else{
            String authToken = request.getHeader("Authorization");
            if(authTokenManager.verifyToken(authToken)){
                chain.doFilter(req, res);
            }
        }
    }
}
