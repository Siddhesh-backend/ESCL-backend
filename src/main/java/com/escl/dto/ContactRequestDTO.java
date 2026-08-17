package com.escl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequestDTO {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String company;

    @NotBlank
    private String phone;

    @NotBlank
    private String matchInterest;

    @NotBlank
    private String message;
}