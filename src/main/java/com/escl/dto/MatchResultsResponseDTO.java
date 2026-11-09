package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class MatchResultsResponseDTO {

    private Long id;
    private String matchTitle;
    private String team1Name;
    private String team2Name;
    private Integer team1Score;
    private Integer team2Score;
    private String winner;
    private String resultSummary;
    private LocalDate matchDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}