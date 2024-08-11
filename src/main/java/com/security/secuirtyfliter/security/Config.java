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

        http
                .addFilterAt(authenticationFilterImp, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests  (requests -> requests
                        .anyRequest().authenticated());

    return http.build();

    }
}
