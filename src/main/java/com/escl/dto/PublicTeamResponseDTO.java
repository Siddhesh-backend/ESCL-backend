package com.escl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PublicTeamResponseDTO {

    private Long id;
    private String teamName;
    private String collegeUniversity;
    private String city;
    private LocalDateTime createdAt;
}
