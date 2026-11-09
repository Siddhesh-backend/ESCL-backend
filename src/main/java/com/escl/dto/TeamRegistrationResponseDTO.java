
package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class TeamRegistrationResponseDTO {

    private Long id;
    private String teamName;
    private String collegeUniversity;
    private String captainFullName;
    private String captainEmail;
    private String city;

    private BigDecimal entryFee;

    private String paymentStatus;
    private String teamStatus;

    private LocalDateTime createdAt;

    private List<PlayerResponseDTO> players;

    @Getter
    @Setter
    public static class PlayerResponseDTO {
        private Long id;
        private String fullName;
        private String collegeId;
        private Integer jerseyNumber;
        private String contactNumber;
    }
}
