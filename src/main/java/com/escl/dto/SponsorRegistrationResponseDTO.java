package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SponsorRegistrationResponseDTO {

    private Long id;

    private String companyName;
    private String industry;
    private String website;

    private String contactFullName;
    private String businessEmail;
    private String mobileNumber;

    private String sponsorshipCategory;
    private String budgetRange;
    private String contributionType;

    private String status;
    private LocalDateTime createdAt;
}
