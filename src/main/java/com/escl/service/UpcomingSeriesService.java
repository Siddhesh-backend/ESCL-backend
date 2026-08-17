
package com.escl.service;

import com.escl.dto.UpcomingSeriesRequestDTO;
import com.escl.dto.UpcomingSeriesResponseDTO;
import com.escl.exception.ResourceNotFoundException;
import com.escl.exception.UpcomingSeriesAlreadyExistsException;
import com.escl.mapper.UpcomingSeriesMapper;
import com.escl.model.UpcomingSeries;
import com.escl.repository.UpcomingSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpcomingSeriesService {

    @Autowired
    private UpcomingSeriesRepository upcomingSeriesRepository;

    // Save
    public UpcomingSeriesResponseDTO saveSeries(
            UpcomingSeriesRequestDTO requestDTO) {

        if (upcomingSeriesRepository.existsBySeriesNameAndSeriesDate(
                requestDTO.getSeriesName(),
                requestDTO.getSeriesDate())) {

            throw new UpcomingSeriesAlreadyExistsException(
                    "Upcoming Series already exists!");
        }

        UpcomingSeries series =
                UpcomingSeriesMapper.toEntity(requestDTO);

        UpcomingSeries savedSeries =
                upcomingSeriesRepository.save(series);

        return UpcomingSeriesMapper.toDTO(savedSeries);
    }

    // Get All
    public List<UpcomingSeriesResponseDTO> getAllSeries() {

        return upcomingSeriesRepository.findAll()
                .stream()
                .map(UpcomingSeriesMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get By Id
    public UpcomingSeriesResponseDTO getSeriesById(Long id) {

        UpcomingSeries series =
                upcomingSeriesRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Upcoming Series Not Found with ID : " + id));

        return UpcomingSeriesMapper.toDTO(series);
    }

    // Update
    public UpcomingSeriesResponseDTO updateSeries(
            Long id,
            UpcomingSeriesRequestDTO requestDTO) {

        UpcomingSeries series =
                upcomingSeriesRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Upcoming Series Not Found with ID : " + id));

        series.setSeriesName(requestDTO.getSeriesName());
        series.setSeriesDate(requestDTO.getSeriesDate());

        UpcomingSeries updatedSeries =
                upcomingSeriesRepository.save(series);

        return UpcomingSeriesMapper.toDTO(updatedSeries);
    }

    // Delete
    public String deleteSeries(Long id) {

        if (!upcomingSeriesRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Upcoming Series Not Found with ID : " + id);
        }

        upcomingSeriesRepository.deleteById(id);

        return "Upcoming Series Deleted Successfully";
    }
}