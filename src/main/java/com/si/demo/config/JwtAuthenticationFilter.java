package com.si.demo.config;

import com.si.demo.service.CounsellorUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CounsellorUserDetailsService userDetailsService;
    private final String SECRET_KEY =
            "my-super-secret-key-for-counsellor-portal-123456789";


    public JwtAuthenticationFilter(CounsellorUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        System.out.println(
                ">>> JwtAuthenticationFilter TRIGGERED: "
                        + request.getMethod()
                        + " "
                        + request.getRequestURI()
        );

        String authHeader = request
                .getHeader("Authorization");

        // No Authorization header
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Extract token
        String token =
                authHeader.substring(7);

        try {
            Claims claims =
                    Jwts.parser()
                            .verifyWith(getSigningKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
            String email = claims.getSubject();

            // Load counsellor from database
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Tell Spring Security that this request is authenticated
            UsernamePasswordAuthenticationToken passwordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(passwordAuthenticationToken);

            System.out.println("JWT User: " + userDetails.getUsername());

            System.out.println("JWT Authorities: " + userDetails.getAuthorities());

            System.out.println( "JWT Authenticated: " + passwordAuthenticationToken.isAuthenticated() );

        } catch (Exception ex) {
            System.out.println(
                    "JWT validation failed: " + ex.getMessage());
        }

        filterChain.doFilter(request, response);



    }

    private SecretKey getSigningKey(){
        return  Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}