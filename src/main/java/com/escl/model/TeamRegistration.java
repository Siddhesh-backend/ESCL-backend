
package com.escl.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_registrations")
@Getter
@Setter
@NoArgsConstructor
public class TeamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Team Details
    @Column(nullable = false)
    private String teamName;

    @Column(nullable = false)
    private String collegeUniversity;

    @Column(nullable = false)
    private String captainFullName;

    @Column(nullable = false)
    private String captainCollegeId;

    @Column(nullable = false)
    private String captainEmail;

    @Column(nullable = false)
    private String captainContactNumber;

    private String alternateContact;

    @Column(nullable = false)
    private String city;

    // Entry Fee Snapshot
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal entryFee;

    // Payment Details
    private String paymentMethod;

    private String transactionReference;

    private String paymentScreenshotPath;

    @Column(nullable = false)
    private String paymentStatus = "PENDING";

    // Team Approval
    @Column(nullable = false)
    private String teamStatus = "PENDING";

    // Timestamps
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
