package com.springboot.api.auth.config;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/* 
 * A filter is an object used to intercept the HTTP requests and responses of your application. 
 * By using filter, we can perform two operations at two instances − 
 * Before sending the request to the controller. Before sending a response to the client.
 */
// OncePerRequestFilter - Filter base class that guarantees to be just executed once per request, 
// on any servlet container.
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
    }

}
