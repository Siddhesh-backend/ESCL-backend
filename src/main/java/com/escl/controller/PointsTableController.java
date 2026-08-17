package com.escl.controller;

import com.escl.dto.PointsTableRequestDTO;
import com.escl.dto.PointsTableResponseDTO;
import com.escl.service.PointsTableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points-table")
public class PointsTableController {

    @Autowired
    private PointsTableService pointsTableService;

    // Save
    @PostMapping
    public ResponseEntity<PointsTableResponseDTO> savePointsTable(
            @Valid @RequestBody PointsTableRequestDTO requestDTO) {

        PointsTableResponseDTO responseDTO =
                pointsTableService.savePointsTable(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get All
    @GetMapping
    public ResponseEntity<List<PointsTableResponseDTO>> getAllPointsTable() {

        return ResponseEntity.ok(
                pointsTableService.getAllPointsTable());
    }

    // Get By Id
    @GetMapping("/{id}")
    public ResponseEntity<PointsTableResponseDTO> getPointsTableById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pointsTableService.getPointsTableById(id));
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<PointsTableResponseDTO> updatePointsTable(
            @PathVariable Long id,
            @Valid @RequestBody PointsTableRequestDTO requestDTO) {

        return ResponseEntity.ok(
                pointsTableService.updatePointsTable(id, requestDTO));
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePointsTable(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                pointsTableService.deletePointsTable(id));
    }
}