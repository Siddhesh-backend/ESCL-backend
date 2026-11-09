package com.escl.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLoginRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}