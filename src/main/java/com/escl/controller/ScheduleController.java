
package com.escl.controller;

import com.escl.dto.ScheduleRequestDTO;
import com.escl.dto.ScheduleResponseDTO;
import com.escl.service.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    // Save
    @PostMapping
    public ResponseEntity<ScheduleResponseDTO> addSchedule(
            @Valid @RequestBody ScheduleRequestDTO requestDTO) {

        ScheduleResponseDTO responseDTO =
                scheduleService.saveSchedule(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDTO>> getAllSchedules() {

        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    // Get By id
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> getScheduleById(
            @PathVariable Long id) {

        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponseDTO> updateSchedule(
            @PathVariable Long id,
            @Valid @RequestBody ScheduleRequestDTO requestDTO) {

        return ResponseEntity.ok(
                scheduleService.updateSchedule(id, requestDTO));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                scheduleService.deleteSchedule(id));
    }
}