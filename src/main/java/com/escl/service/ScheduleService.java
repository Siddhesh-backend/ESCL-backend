package com.escl.service;

import com.escl.dto.ScheduleRequestDTO;
import com.escl.dto.ScheduleResponseDTO;
import com.escl.exception.ResourceNotFoundException;
import com.escl.exception.ScheduleAlreadyExistsException;
import com.escl.mapper.ScheduleMapper;
import com.escl.model.Schedule;
import com.escl.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    // Save Schedule
    public ScheduleResponseDTO saveSchedule(ScheduleRequestDTO requestDTO) {

        if (scheduleRepository.existsBySeriesNameAndMatchNameAndMatchDate(
                requestDTO.getSeriesName(),
                requestDTO.getMatchName(),
                requestDTO.getMatchDate())) {

            throw new ScheduleAlreadyExistsException(
                    "Schedule already exists");
        }

        Schedule schedule = ScheduleMapper.toEntity(requestDTO);

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleMapper.toDTO(savedSchedule);
    }

    // Get All Schedules
    public List<ScheduleResponseDTO> getAllSchedules() {

        return scheduleRepository.findAll()
                .stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Schedule By Id
    public ScheduleResponseDTO getScheduleById(Long id) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Schedule Not Found with ID : " + id));

        return ScheduleMapper.toDTO(schedule);
    }

    // Update Schedule
    public ScheduleResponseDTO updateSchedule(
            Long id,
            ScheduleRequestDTO requestDTO) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Schedule Not Found with ID : " + id));

        schedule.setSeriesName(requestDTO.getSeriesName());
        schedule.setMatchName(requestDTO.getMatchName());
        schedule.setVenue(requestDTO.getVenue());
        schedule.setCity(requestDTO.getCity());
        schedule.setMatchDate(requestDTO.getMatchDate());
        schedule.setMatchTime(requestDTO.getMatchTime());
        schedule.setGmtTime(requestDTO.getGmtTime());
        schedule.setLocalTime(requestDTO.getLocalTime());
        schedule.setStatus(requestDTO.getStatus());

        Schedule updatedSchedule = scheduleRepository.save(schedule);

        return ScheduleMapper.toDTO(updatedSchedule);
    }

    // Delete Schedule
    public String deleteSchedule(Long id) {

        if (!scheduleRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "Schedule Not Found with ID : " + id);
        }

        scheduleRepository.deleteById(id);

        return "Schedule Deleted Successfully";
    }
}