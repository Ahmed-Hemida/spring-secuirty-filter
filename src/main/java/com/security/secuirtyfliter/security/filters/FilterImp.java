package com.security.secuirtyfliter.security.filters;

import java.io.IOException;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.secuirtyfliter.security.auth.AuthenticationImp;
import com.security.secuirtyfliter.security.managers.AuthManagerImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class FilterImp extends OncePerRequestFilter {
 private final AuthManagerImp manger; 
    @Override
    protected void doFilterInternal( HttpServletRequest request,
                                     HttpServletResponse response,
                                     FilterChain filterChain)
            throws ServletException, IOException {

        // request header variable
        String headerKey = request.getHeader("key");

        var authImpl = manger.authenticate(new AuthenticationImp(false, headerKey));
        if(authImpl.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authImpl);
            filterChain.doFilter(request, response);
        }
       
    }
 
    
    
}
