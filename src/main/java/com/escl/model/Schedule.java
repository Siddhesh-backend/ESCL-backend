package com.escl.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Series Name is required")
    private String seriesName;

    @NotBlank(message = "Match Name is required")
    private String matchName;

    @NotBlank(message = "Venue is required")
    private String venue;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Match Date is required")
    private String matchDate;

    @NotBlank(message = "Match Time is required")
    private String matchTime;

    @NotBlank(message = "GMT Time is required")
    private String gmtTime;

    @NotBlank(message = "Local Time is required")
    private String localTime;

    @NotBlank(message = "Status is required")
    private String status;
}