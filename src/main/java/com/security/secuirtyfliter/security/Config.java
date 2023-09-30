package com.security.secuirtyfliter.security;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.http.SecurityHeaders;

import com.security.secuirtyfliter.security.filters.FilterImp;

import jakarta.servlet.ServletException;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Config {
    
 
    private final FilterImp authenticationFilterImp;
 
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception{

        return http
        .addFilterAt(authenticationFilterImp, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().anyRequest().authenticated()  // don't worry about this
        .and().build();

    }

    private FilterImp extracted() {
        return authenticationFilterImp;
    }
}
