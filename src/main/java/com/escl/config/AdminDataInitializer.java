
package com.escl.config;

import com.escl.model.Admin;
import com.escl.repository.AdminRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Value("${app.admin.email:}")
    private String adminEmail;

    @Value("${app.admin.password:}")
    private String adminPassword;

    @Bean
    public ApplicationRunner createInitialAdmin(
            AdminRepository adminRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            if (adminEmail.isBlank() || adminPassword.isBlank()) {
                return;
            }

            if (adminRepository.findByEmail(adminEmail).isPresent()) {
                System.out.println("Existing admin found. Password unchanged.");
                return;
            }

            Admin admin = new Admin();

            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole("ADMIN");

            adminRepository.save(admin);

            System.out.println("Initial admin created successfully.");
        };
    }
}
