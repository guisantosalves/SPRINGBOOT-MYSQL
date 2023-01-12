package com.springboot.api.auth.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.api.auth.controllers.AuthenticationRequest;
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
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user = User
        .builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
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

    public AuthenticationResponse register(AuthenticationRequest request){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        ); // valida no banco

        //busca no banco
        var user = repo
        .findByEmail(request.getEmail())
        .orElseThrow(() -> new IllegalStateException("User does not exist"));
        
        var jwtToken = jwtService.generateToken(user); // gera o tokent com base no dado do banco
        
        return AuthenticationResponse
        .builder()
        .Token(jwtToken)
        .build();
    }

}
