package com.security.secuirtyfliter.security.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.security.secuirtyfliter.security.auth.AuthenticationImp;

@Component

public class AuthProviderImp  implements AuthenticationProvider{
    @Value("${spring.security.local.key}")
     String key;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //here you check the Auth credential 
       //here logic for authentication {token , secrt key} 
       AuthenticationImp CA = (AuthenticationImp) authentication;
       String headerKey = CA.getKey(); //get key fron class
        if(headerKey.equals(key))
            return new AuthenticationImp(true,null);

        throw new BadCredentialsException("key is not correct");

    }

    @Override
    public boolean supports(Class<?> authentication) {
       
        return AuthenticationImp.class.equals(authentication);
    }

    
}
