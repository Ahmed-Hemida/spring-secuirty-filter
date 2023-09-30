package com.security.secuirtyfliter.security.managers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.security.secuirtyfliter.security.providers.AuthProviderImp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component

public class AuthManagerImp implements AuthenticationManager {
   private final AuthProviderImp provider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        if(provider.supports(authentication.getClass()))
            return provider.authenticate(authentication);
        return null;
    }
    public AuthProviderImp getProvider() {
        return provider;
    }
    
}
