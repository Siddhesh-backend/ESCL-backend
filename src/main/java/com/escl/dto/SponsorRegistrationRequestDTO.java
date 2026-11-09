
package com.escl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SponsorRegistrationRequestDTO {

    // Company Details
    @NotBlank
    private String companyName;

    @NotBlank
    private String industry;

    private String website;

    // Contact Details
    @NotBlank
    private String contactFullName;

    @NotBlank
    private String designation;

    @NotBlank
    @Email
    private String businessEmail;

    @NotBlank
    private String mobileNumber;

    private String preferredContactMethod;

    // Sponsorship Details
    @NotBlank
    private String sponsorshipCategory;

    @NotBlank
    private String budgetRange;

    @NotBlank
    private String contributionType;

    private String promotionPreferences;

    @Size(max = 1000)
    private String message;

    // Consent
    @NotNull
    @AssertTrue(message = "Accuracy confirmation is required")
    private Boolean accuracyConfirmed;

    private Boolean contactConsent;
}
