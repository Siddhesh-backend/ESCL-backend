package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TournamentDetailsRequestDTO {

    @NotBlank
    private String tournamentName;

    @Size(max = 2000)
    private String description;

    private String venue;

    private LocalDate startDate;

    private LocalDate endDate;

    private String rules;
}
