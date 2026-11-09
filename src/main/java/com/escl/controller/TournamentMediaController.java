package com.escl.controller;

import com.escl.dto.TournamentMediaRequestDTO;
import com.escl.dto.TournamentMediaResponseDTO;
import com.escl.service.TournamentMediaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament-media")
@RequiredArgsConstructor
public class TournamentMediaController {

    private final TournamentMediaService service;

    @PostMapping
    public ResponseEntity<TournamentMediaResponseDTO> createMedia(
            @Valid @RequestBody TournamentMediaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createMedia(dto));
    }

    @GetMapping
    public ResponseEntity<List<TournamentMediaResponseDTO>> getAllMedia() {
        return ResponseEntity.ok(service.getAllMedia());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentMediaResponseDTO> getMediaById(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.getMediaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TournamentMediaResponseDTO> updateMedia(
            @PathVariable Long id,
            @Valid @RequestBody TournamentMediaRequestDTO dto) {
        return ResponseEntity.ok(service.updateMedia(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        service.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}