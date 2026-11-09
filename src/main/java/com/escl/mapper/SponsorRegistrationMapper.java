
package com.escl.mapper;

import com.escl.dto.SponsorRegistrationRequestDTO;
import com.escl.dto.SponsorRegistrationResponseDTO;
import com.escl.model.SponsorRegistration;

public class SponsorRegistrationMapper {

    private SponsorRegistrationMapper() {
    }

    public static SponsorRegistration toEntity(
            SponsorRegistrationRequestDTO dto) {

        SponsorRegistration sponsor = new SponsorRegistration();

        sponsor.setCompanyName(dto.getCompanyName());
        sponsor.setIndustry(dto.getIndustry());
        sponsor.setWebsite(dto.getWebsite());

        sponsor.setContactFullName(dto.getContactFullName());
        sponsor.setDesignation(dto.getDesignation());
        sponsor.setBusinessEmail(dto.getBusinessEmail());
        sponsor.setMobileNumber(dto.getMobileNumber());
        sponsor.setPreferredContactMethod(dto.getPreferredContactMethod());

        sponsor.setSponsorshipCategory(dto.getSponsorshipCategory());
        sponsor.setBudgetRange(dto.getBudgetRange());
        sponsor.setContributionType(dto.getContributionType());
        sponsor.setPromotionPreferences(dto.getPromotionPreferences());
        sponsor.setMessage(dto.getMessage());

        sponsor.setAccuracyConfirmed(dto.getAccuracyConfirmed());
        sponsor.setContactConsent(dto.getContactConsent());

        return sponsor;
    }

    public static SponsorRegistrationResponseDTO toResponse(
            SponsorRegistration sponsor) {

        SponsorRegistrationResponseDTO dto =
                new SponsorRegistrationResponseDTO();

        dto.setId(sponsor.getId());
        dto.setCompanyName(sponsor.getCompanyName());
        dto.setIndustry(sponsor.getIndustry());
        dto.setWebsite(sponsor.getWebsite());

        dto.setContactFullName(sponsor.getContactFullName());
        dto.setBusinessEmail(sponsor.getBusinessEmail());
        dto.setMobileNumber(sponsor.getMobileNumber());

        dto.setSponsorshipCategory(sponsor.getSponsorshipCategory());
        dto.setBudgetRange(sponsor.getBudgetRange());
        dto.setContributionType(sponsor.getContributionType());

        dto.setStatus(sponsor.getStatus());
        dto.setCreatedAt(sponsor.getCreatedAt());

        return dto;
    }
}
