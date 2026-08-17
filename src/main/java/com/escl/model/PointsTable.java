package com.escl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="points_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PointsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message ="Season is required")
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