package com.security.secuirtyfliter.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.security.secuirtyfliter.security.auth.AuthenticationImp;

@Component

public class AuthProviderImp  implements AuthenticationProvider{
    @Value("${spring.security.local.key}")
    private String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //here you check the Auth credential 
       //here logic for authentication {token , secrt key} 
       AuthenticationImp customAuth = (AuthenticationImp) authentication;
       String headerKey = customAuth.getKey(); //get key fron class
        if(headerKey.equals(key))
            return new AuthenticationImp(true,null);

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "key is not correct");

    }

    @Override
    public boolean supports(Class<?> authentication) {
       
        return AuthenticationImp.class.equals(authentication);
    }

    
}
