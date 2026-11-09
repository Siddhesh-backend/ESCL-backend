
package com.escl.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TeamRegistrationRequestDTO {

    @NotBlank
    private String teamName;

    @NotBlank
    private String collegeUniversity;

    @NotBlank
    private String captainFullName;

    @NotBlank
    private String captainCollegeId;

    @NotBlank
    @Email
    private String captainEmail;

    @NotBlank
    private String captainContactNumber;

    private String alternateContact;

    @NotBlank
    private String city;

    @NotBlank
    private String paymentMethod;

    private String transactionReference;

    @NotEmpty
    @Size(max = 15)
    @Valid
    private List<PlayerDTO> players;

    @Getter
    @Setter
    public static class PlayerDTO {

        @NotBlank
        private String fullName;

        @NotBlank
        private String collegeId;

        private Integer jerseyNumber;

        private String contactNumber;
    }
}
