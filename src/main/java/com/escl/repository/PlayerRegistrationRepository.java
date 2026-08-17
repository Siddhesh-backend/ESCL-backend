package com.escl.repository;

import com.escl.model.PlayerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRegistrationRepository extends JpaRepository<PlayerRegistration, Long> {

    Optional<PlayerRegistration> findByEmail(String email);

    boolean existsByEmail(String email);
}