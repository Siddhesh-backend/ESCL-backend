package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactResponseDTO {

    private Long id;

    private String name;

    private String email;

    private String company;

    private String phone;

    private String matchInterest;

    private String message;
}