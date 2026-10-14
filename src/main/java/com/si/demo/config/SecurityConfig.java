package com.si.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager
            (AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST,
                                "/api/counsellors/register",
                                "/api/counsellors/login"
                        ).permitAll()

                        // Courses
                        .requestMatchers(HttpMethod.GET,
                                "/api/courses",
                                "/api/courses/**"
                        ).permitAll()

                        // Save enquiry
                        .requestMatchers(HttpMethod.POST,
                                "/api/enquiries"
                        ).hasRole("COUNSELLOR")
                        //.anyRequest().authenticated()

                        //Search enquiries
                        .requestMatchers(HttpMethod.GET,
                                "/api/enquiries/search"
                                ).hasRole("COUNSELLOR")
                        .anyRequest().authenticated()
                )

                /*Step 3.8 — Make JWT authentication stateless
                //Right now Spring Security can still use its default session behavior.
                With JWT, we want every request to be authenticated using the JWT, not a server-side login session.
                  */
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //WHY - "Run my JWT filter before Spring Security's UsernamePasswordAuthenticationFilter."
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return httpSecurity.build();
    }
}

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){
//
//        httpSecurity
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/api/counsellors/register", "/api/counsellors/login", "/api/enquiries/**").permitAll()
////                        .requestMatchers(HttpMethod.GET,
////                                "/api/courses", "/api/courses/**").permitAll()
//                                // Public registration and login
//                                .requestMatchers(HttpMethod.POST,
//                                        "/api/counsellors/register",
//                                        "/api/counsellors/login"
//                                ).permitAll()
//
//                                // Courses
//                                .requestMatchers(HttpMethod.GET,
//                                        "/api/courses",
//                                        "/api/courses/**"
//                                ).permitAll()
//
//                                // Save enquiry
//                                .requestMatchers(HttpMethod.POST,
//                                        "/api/enquiries"
//                                ).permitAll()
//                        .anyRequest().authenticated()
//                );
//        return httpSecurity.build();
//    }

