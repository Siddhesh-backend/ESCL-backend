
package com.escl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Primary
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
                .cors(cors -> {})
                .csrf(csrf -> csrf.disable())
                .securityContext(context -> context.requireExplicitSave(false))
                .authorizeHttpRequests(auth -> auth

                        // CORS Preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Admin Authentication
                        .requestMatchers(
                                HttpMethod.POST,
                                "/admin/auth/login"
                        ).permitAll()

                        .requestMatchers(
                                HttpMethod.POST,
                                "/admin/auth/logout"
                        ).hasRole("ADMIN")

                        // Admin Team Registration Settings
                        .requestMatchers(
                                "/admin/team-registration-settings/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/team-registrations/*/payment-status"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                "/admin/team-registrations/**"
                        ).hasRole("ADMIN")

                        // Admin Sponsor Registration
                        .requestMatchers(
                                "/admin/sponsor-registrations/**"
                        ).hasRole("ADMIN")

                        // Admin tournament details
                        .requestMatchers(
                                HttpMethod.POST,
                                "/tournament-details"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/tournament-details/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/tournament-details/*"
                        ).hasRole("ADMIN")

                        // Player Login
                        .requestMatchers(
                                "/players/login"
                        ).permitAll()

                        // Public Player Registration
                        .requestMatchers(
                                HttpMethod.POST,
                                "/players"
                        ).permitAll()

                        // Public Team Registration
                        .requestMatchers(
                                HttpMethod.POST,
                                "/team-registrations"
                        ).permitAll()

                        // Public Sponsor Registration
                        .requestMatchers(
                                HttpMethod.POST,
                                "/sponsor-registrations"
                        ).permitAll()

                        // Public Approved Team List
                        .requestMatchers(
                                HttpMethod.GET,
                                "/team-registrations/public"
                        ).permitAll()

                        // Public Approved Sponsor List
                        .requestMatchers(
                                HttpMethod.GET,
                                "/sponsor-registrations/public"
                        ).permitAll()

                        // Public tournament-details
                        .requestMatchers(
                                HttpMethod.GET,
                                "/tournament-details",
                                "/tournament-details/*"
                        ).permitAll()

                        // Player Admin Operations
                        .requestMatchers(
                                HttpMethod.PUT,
                                "/players/*"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/players/*"
                        ).hasRole("ADMIN")

                        // Authenticated Player APIs
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
                        ).permitAll()

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
                        ).permitAll()

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
                        ).permitAll()

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

                        // Match Results - Admin Only
                        .requestMatchers(HttpMethod.POST, "/match-results").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/match-results/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/match-results/*").hasRole("ADMIN")

                        // Match Results - Public Access
                        .requestMatchers(HttpMethod.GET, "/match-results", "/match-results/*").permitAll()

                        // Tournament Media - Admin Only
                        .requestMatchers(HttpMethod.POST, "/tournament-media").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/tournament-media/*").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/tournament-media/*").hasRole("ADMIN")

                        // Tournament Media - Public Access
                        .requestMatchers(HttpMethod.GET, "/tournament-media", "/tournament-media/*").permitAll()

                        // All remaining APIs
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
