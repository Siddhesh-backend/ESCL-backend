package com.escl.repository;

import com.escl.model.SponsorRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsorRegistrationRepository
        extends JpaRepository<SponsorRegistration, Long> {

    List<SponsorRegistration> findAllByStatusIgnoreCaseOrderByCreatedAtDesc(
            String status
    );
}