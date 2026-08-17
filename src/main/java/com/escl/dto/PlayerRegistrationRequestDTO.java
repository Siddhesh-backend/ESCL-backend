package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRegistrationRequestDTO {

    @NotBlank
    private String fullName;

    @NotBlank
    private String collegeName;

    @NotBlank
    private String branch;

    @NotBlank
    private String year;

    @NotBlank
    private String dob;

    @NotBlank
    private String position;

    @NotBlank
    private String experience;

    @NotBlank
    private String cricheroesUrl;

    private double basePrice;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String paymentMethod;
}