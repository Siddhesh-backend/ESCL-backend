package com.escl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicSponsorResponseDTO {

    private Long id;
    private String companyName;
    private String industry;
    private String website;
    private String sponsorshipCategory;
    private String contributionType;
}