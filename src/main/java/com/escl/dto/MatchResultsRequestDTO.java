package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatchResultsRequestDTO {

    @NotBlank
    private String matchTitle;

    @NotBlank
    private String team1Name;

    @NotBlank
    private String team2Name;

    @NotNull
    @Min(0)
    private Integer team1Score;

    @NotNull
    @Min(0)
    private Integer team2Score;

    private String winner;

    @Size(max = 1000)
    private String resultSummary;

    private LocalDate matchDate;
}