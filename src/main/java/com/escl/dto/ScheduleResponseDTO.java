package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleResponseDTO {

    private Long id;

    private String seriesName;

    private String matchName;

    private String venue;

    private String city;

    private String matchDate;

    private String matchTime;

    private String gmtTime;

    private String localTime;

    private String status;
}
