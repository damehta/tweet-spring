package com.dam.tweetspringdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    AuthTokenManager authTokenManager;

    public static final String USER_LOGIN_PATH="/user/login";

    protected void configure(HttpSecurity http) throws Exception{
        http.addFilterBefore(new CustomChannelProcessingFilter(authTokenManager), AnonymousAuthenticationFilter.class)
                .authorizeRequests()
                .regexMatchers(USER_LOGIN_PATH).fullyAuthenticated()
                .and().httpBasic()
                .and().csrf().disable()
                .sessionManagement().disable();

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder)
        throws Exception {
        authenticationManagerBuilder.authenticationProvider(new LdapAuthenticationProvider()).eraseCredentials(false);

    }
}
