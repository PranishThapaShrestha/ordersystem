package com.buddha.ordersystem.auth.authService;

import com.buddha.ordersystem.auth.config.JwtConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final JwtConfig jwtConfig;

    public JwtService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String getSecretKey() {
        return jwtConfig.gerSecret();
    }

    public String generateAccessToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(Keys.hmacShaKeyFor(getSecretKey().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String ExtractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey().getBytes(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJwt(token)
                .getBody()
                .getSubject();
    }



}

