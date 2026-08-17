package com.escl.mapper;

import com.escl.dto.ScheduleRequestDTO;
import com.escl.dto.ScheduleResponseDTO;
import com.escl.model.Schedule;

public class ScheduleMapper {

    public static Schedule toEntity(ScheduleRequestDTO dto){

        Schedule schedule = new Schedule();

        schedule.setSeriesName(dto.getSeriesName());

        schedule.setMatchName(dto.getMatchName());

        schedule.setVenue(dto.getVenue());

        schedule.setCity(dto.getCity());

        schedule.setMatchDate(dto.getMatchDate());

        schedule.setMatchTime(dto.getMatchTime());

        schedule.setGmtTime(dto.getGmtTime());

        schedule.setLocalTime(dto.getLocalTime());

        schedule.setStatus(dto.getStatus());
        return schedule;
    }

    public static ScheduleResponseDTO toDTO(Schedule schedule){

        ScheduleResponseDTO dto = new ScheduleResponseDTO();

        dto.setId(schedule.getId());

        dto.setSeriesName(schedule.getSeriesName());

        dto.setMatchName(schedule.getMatchName());

        dto.setVenue(schedule.getVenue());

        dto.setCity(schedule.getCity());

        dto.setMatchDate(schedule.getMatchDate());

        dto.setMatchTime(schedule.getMatchTime());

        dto.setGmtTime(schedule.getGmtTime());

        dto.setLocalTime(schedule.getLocalTime());

        dto.setStatus(schedule.getStatus());
        return dto;
    }

}