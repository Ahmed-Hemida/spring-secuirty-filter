package com.security.secuirtyfliter.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.security.secuirtyfliter.security.filters.AuthFilterImp;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class Config {
    
 
    private final AuthFilterImp authenticationFilterImp;
 
    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http) throws Exception{

        return http
        .addFilterAt(authenticationFilterImp, UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests().anyRequest().authenticated()  // don't worry about this
        .and().build();

    }

    private AuthFilterImp extracted() {
        return authenticationFilterImp;
    }
}
