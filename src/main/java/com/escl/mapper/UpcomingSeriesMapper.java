package com.escl.mapper;

import com.escl.dto.UpcomingSeriesRequestDTO;
import com.escl.dto.UpcomingSeriesResponseDTO;
import com.escl.model.UpcomingSeries;

public class UpcomingSeriesMapper {

    public static UpcomingSeries toEntity(UpcomingSeriesRequestDTO dto){

        UpcomingSeries series = new UpcomingSeries();

        series.setSeriesName(dto.getSeriesName());
        series.setSeriesDate(dto.getSeriesDate());

        return series;
    }

    public static UpcomingSeriesResponseDTO toDTO(UpcomingSeries series){

        UpcomingSeriesResponseDTO dto = new UpcomingSeriesResponseDTO();

        dto.setId(series.getId());
        dto.setSeriesName(series.getSeriesName());
        dto.setSeriesDate(series.getSeriesDate());

        return dto;
    }
}