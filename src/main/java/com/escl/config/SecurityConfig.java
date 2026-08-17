package com.escl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .securityContext(context -> context.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth
                        // CORS Preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Public
                        .requestMatchers(
                                "/players/login"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/players"
                        ).permitAll()

                        // Admin only
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/players/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/players/*"
                        ).hasRole("ADMIN")

                        // Authenticated users
                        .requestMatchers(
                                HttpMethod.GET,
                                "/players",
                                "/players/*"
                        ).authenticated()
                        // Schedule
                        .requestMatchers(
                                HttpMethod.POST,
                                "/schedules"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/schedules/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/schedules/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/schedules",
                                "/schedules/*"
                        ).authenticated()
                        // Upcoming Series
                        .requestMatchers(
                                HttpMethod.POST,
                                "/upcoming-series"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/upcoming-series/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/upcoming-series/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/upcoming-series",
                                "/upcoming-series/*"
                        ).authenticated()
                        // Points Table
                        .requestMatchers(
                                HttpMethod.POST,
                                "/points-table"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/points-table/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/points-table/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/points-table",
                                "/points-table/*"
                        ).authenticated()
                        // Contact
                        .requestMatchers(
                                HttpMethod.POST,
                                "/contacts"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.GET,
                                "/contacts",
                                "/contacts/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/contacts/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/contacts/*"
                        ).hasRole("ADMIN")

                        .anyRequest().authenticated()
                );
        return http.build();
    }
}