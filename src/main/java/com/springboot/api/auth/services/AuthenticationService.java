package com.springboot.api.auth.services;

import org.springframework.stereotype.Service;

import com.springboot.api.auth.controllers.AuthenticationResponse;
import com.springboot.api.auth.controllers.RegisterRequest;
import com.springboot.api.auth.repository.UserRepository;
import com.springboot.api.auth.user.Role;
import com.springboot.api.auth.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    
    private final UserRepository repo;
    private final JwtService jwtService;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User
        .builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(request.getPassword())
        .role(Role.USER)
        .build();

        // cadastra no banco
        repo.save(user);
        
        // gera token pra mandar de volta
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
        .builder()
        .Token(jwtToken)
        .build();
    }

}
