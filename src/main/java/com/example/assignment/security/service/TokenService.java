package com.example.assignment.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService {

    private final SecretKey secretKey;

    @Value("${loginTokenExpirationMilliSecond}")
    private long expirationMS;

    public TokenService(@Value("${tokenKey}") String key) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
    }

    public String generateLoginToken(Long userId) {

        return initJwtToken(userId, expirationMS);
    }

    public boolean validateToken(String authToken) {

        if (ObjectUtils.isEmpty(authToken)) {
            return false;
        }

        try {
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(authToken);
            return true;
        } catch (JwtException ex) {
            return false;
        }
    }

    public Long getUserIdFromToken(String token) {

        Claims claims = Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get("user", Long.class);
    }

    private String initJwtToken(Object userId, long expirationSecond) {

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationSecond);

        return Jwts.builder()
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .claims(Map.of("user", userId))
                .compact();
    }
}
