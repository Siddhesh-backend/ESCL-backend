package com.escl.controller;

import com.escl.dto.MatchResultsRequestDTO;
import com.escl.dto.MatchResultsResponseDTO;
import com.escl.service.MatchResultsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match-results")
@RequiredArgsConstructor
public class MatchResultsController {

    private final MatchResultsService service;

    @PostMapping
    public ResponseEntity<MatchResultsResponseDTO> createMatchResult(
            @Valid @RequestBody MatchResultsRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createMatchResult(dto));
    }

    @GetMapping
    public ResponseEntity<List<MatchResultsResponseDTO>> getAllMatchResults() {
        return ResponseEntity.ok(service.getAllMatchResults());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchResultsResponseDTO> getMatchResultById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getMatchResultById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatchResultsResponseDTO> updateMatchResult(
            @PathVariable Long id,
            @Valid @RequestBody MatchResultsRequestDTO dto) {
        return ResponseEntity.ok(service.updateMatchResult(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatchResult(@PathVariable Long id) {
        service.deleteMatchResult(id);
        return ResponseEntity.noContent().build();
    }
}