package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PointsTableResponseDTO {

    private Long id;
    private String season;
    private int position;
    private String teamName;
    private int matchesPlayed;
    private int won;
    private int lost;
    private int tie;
    private int noResult;
    private double netRunRate;
    private int points;
}