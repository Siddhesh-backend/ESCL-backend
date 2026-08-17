package com.escl.controller;

import com.escl.dto.UpcomingSeriesRequestDTO;
import com.escl.dto.UpcomingSeriesResponseDTO;
import com.escl.service.UpcomingSeriesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/upcoming-series")
public class UpcomingSeriesController {

    @Autowired
    private UpcomingSeriesService upcomingSeriesService;

    // Save
    @PostMapping
    public ResponseEntity<UpcomingSeriesResponseDTO> addUpcomingSeries(
            @Valid @RequestBody UpcomingSeriesRequestDTO requestDTO) {

        UpcomingSeriesResponseDTO responseDTO =
                upcomingSeriesService.saveSeries(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<UpcomingSeriesResponseDTO>>
    getAllUpcomingSeries() {

        return ResponseEntity.ok(
                upcomingSeriesService.getAllSeries());
    }

    // Get By Id
    @GetMapping("/{id}")
    public ResponseEntity<UpcomingSeriesResponseDTO>
    getUpcomingSeriesById(@PathVariable Long id) {

        return ResponseEntity.ok(
                upcomingSeriesService.getSeriesById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<UpcomingSeriesResponseDTO>
    updateUpcomingSeries(
            @PathVariable Long id,
            @Valid @RequestBody UpcomingSeriesRequestDTO requestDTO) {

        return ResponseEntity.ok(
                upcomingSeriesService.updateSeries(id, requestDTO));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUpcomingSeries(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                upcomingSeriesService.deleteSeries(id));
    }
}