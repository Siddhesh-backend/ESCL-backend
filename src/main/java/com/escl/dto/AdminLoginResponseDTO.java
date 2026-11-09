package com.escl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminLoginResponseDTO {

    private String message;
    private String email;
    private String role;
}
