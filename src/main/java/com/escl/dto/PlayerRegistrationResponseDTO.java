package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerRegistrationResponseDTO {

    private Long id;

    private String fullName;

    private String collegeName;

    private String branch;

    private String year;

    private String dob;

    private String position;

    private String experience;

    private String cricheroesUrl;

    private double basePrice;

    private String email;

    private String paymentMethod;

    private String role;
}