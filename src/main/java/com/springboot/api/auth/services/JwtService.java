package com.springboot.api.auth.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    private static final String SECRET_KEY = "6B59703373367639792442264529482B4D6251655468576D5A7134743777217A";

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> ClaimsResolver){
        final Claims claims = extractAllClaims(token); 
        return ClaimsResolver.apply(claims);// getAllClaims from body and execute getSubject
    }

    public Claims extractAllClaims(String token){
        return Jwts
        .parserBuilder()
        .setSigningKey(generatingSignKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    public Key generatingSignKey(){
        // hex -> base64 -> array de bytes
        byte[] KeyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(KeyBytes);
    }

    public String generateTokenByhashMap(
        Map<String, Claims> ExtraClaims,
        UserDetails userDetails
    ){
        return "Bearer " + Jwts
        .builder()
        .setClaims(ExtraClaims)
        .setSubject(userDetails.getUsername()) // que no caso é o email
        .setIssuedAt(new java.util.Date(System.currentTimeMillis())) // emitido em
        .setExpiration(new java.util.Date(System.currentTimeMillis() + 1000 * 60 * 24))
        .signWith(generatingSignKey())
        .compact();
    }

    public String generateToken(UserDetails userDetails){
        return generateTokenByhashMap(new HashMap<>(), userDetails);
    }

    public boolean isTokenExpired(String token) {
        return ExtractExpirationToken(token).before(new Date());
    }

    public Date ExtractExpirationToken(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean IstokenValid(String token, UserDetails user){
        var subjectFromToken = extractClaim(token, Claims::getSubject);
        
        return (user.getUsername().equals(subjectFromToken) && !isTokenExpired(token));
    }
}
