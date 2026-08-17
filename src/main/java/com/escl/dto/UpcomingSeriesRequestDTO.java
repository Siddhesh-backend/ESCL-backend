package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpcomingSeriesRequestDTO {

    @NotBlank(message = "Series name is required")
    private String seriesName;

    @NotBlank(message = "Series date is required")
    private String seriesDate;
}