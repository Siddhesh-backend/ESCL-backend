package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class TournamentDetailsResponseDTO {

    private Long id;

    private String tournamentName;

    private String description;

    private String venue;

    private LocalDate startDate;

    private LocalDate endDate;

    private String rules;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
