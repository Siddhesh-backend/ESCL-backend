package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointsTableRequestDTO {

    @NotBlank(message = "Season is required")
    private String season;

    private int position;

    @NotBlank(message = "Team Name is required")
    private String teamName;

    private int matchesPlayed;
    private int won;
    private int lost;
    private int tie;
    private int noResult;
    private double netRunRate;
    private int points;
}