package com.example.demo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;   // <- make sure this import is there

import java.util.Date;
import java.util.Set;

@Component   // <<< THIS is what Spring needs
public class JwtUtil {

    private final String SECRET_KEY = "secret123"; 
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String email, Long userId, Set<String> roles) {
        return Jwts.builder()
                .setSubject(email)
                .claim("userId", userId)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
