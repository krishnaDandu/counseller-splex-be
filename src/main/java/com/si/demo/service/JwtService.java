package com.si.demo.service;

import com.si.demo.entity.Counsellor;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.JwtDsl;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY =  "my-super-secret-key-for-counsellor-portal-123456789";

    public String generateToken(Counsellor counsellor){

        return Jwts.builder()
                .subject(counsellor.getEmail())
                .claim("counsellorId", counsellor.getCounsellorId())
                .claim("name", counsellor.getName())
                .claim("role", "COUNSELLOR")
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis() + 1000 * 60 *60
                        )
                )
                .signWith(getSigningKey())
                .compact();

    }

    private SecretKey getSigningKey(){
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }



}
