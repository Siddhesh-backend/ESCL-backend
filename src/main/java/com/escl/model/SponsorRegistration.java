
package com.escl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sponsor_registrations")
@Getter
@Setter
@NoArgsConstructor
public class SponsorRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Company Details
    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String industry;

    private String website;

    // Contact Details
    @Column(nullable = false)
    private String contactFullName;

    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String businessEmail;

    @Column(nullable = false)
    private String mobileNumber;

    private String preferredContactMethod;

    // Sponsorship Details
    @Column(nullable = false)
    private String sponsorshipCategory;

    @Column(nullable = false)
    private String budgetRange;

    @Column(nullable = false)
    private String contributionType;

    private String promotionPreferences;

    @Column(length = 1000)
    private String message;

    // Admin Review
    @Column(nullable = false)
    private String status = "PENDING";

    // Consent
    @Column(nullable = false)
    private Boolean accuracyConfirmed;

    private Boolean contactConsent;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

