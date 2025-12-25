package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            JwtFilter jwtFilter,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint
    ) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // PUBLIC
                .requestMatchers(
                        "/auth/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/h2-console/**"
                ).permitAll()

                // ADMIN ONLY
                .requestMatchers(
                        "/api/depreciation-rules/**",
                        "/api/vendors/**",
                        "/api/asset-disposals/**"
                ).hasRole("ADMIN")

                // USER + ADMIN
                .requestMatchers(
                        "/api/assets/**",
                        "/api/asset-events/**"
                ).hasAnyRole("USER", "ADMIN")

                .anyRequest().authenticated()
            )

            .exceptionHandling(ex ->
                ex.authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )

            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .headers(headers ->
                headers.frameOptions(frame -> frame.disable())
            )

            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
