package com.escl.controller;

import com.escl.dto.TournamentDetailsRequestDTO;
import com.escl.dto.TournamentDetailsResponseDTO;
import com.escl.service.TournamentDetailsService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament-details")
@RequiredArgsConstructor
public class TournamentDetailsController {

    private final TournamentDetailsService tournamentService;

    // Admin: Create Tournament
    @PostMapping
    public ResponseEntity<TournamentDetailsResponseDTO> createTournament(
            @Valid @RequestBody TournamentDetailsRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(tournamentService.createTournament(dto));
    }

    // Public: Get All Tournaments
    @GetMapping
    public ResponseEntity<List<TournamentDetailsResponseDTO>> getAllTournaments() {

        return ResponseEntity.ok(
                tournamentService.getAllTournaments());
    }

    // Public: Get Tournament By ID
    @GetMapping("/{id}")
    public ResponseEntity<TournamentDetailsResponseDTO> getTournamentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tournamentService.getTournamentById(id));
    }

    // Admin: Update Tournament
    @PutMapping("/{id}")
    public ResponseEntity<TournamentDetailsResponseDTO> updateTournament(
            @PathVariable Long id,
            @Valid @RequestBody TournamentDetailsRequestDTO dto) {

        return ResponseEntity.ok(
                tournamentService.updateTournament(id, dto));
    }

    // Admin: Delete Tournament
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTournament(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                tournamentService.deleteTournament(id));
    }
}