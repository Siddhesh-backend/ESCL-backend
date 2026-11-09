
package com.escl.controller;

import com.escl.dto.TeamRegistrationResponseDTO;
import com.escl.service.TeamRegistrationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/team-registrations")
@RequiredArgsConstructor
public class TeamRegistrationAdminController {

    private final TeamRegistrationService teamRegistrationService;

    @GetMapping
    public ResponseEntity<List<TeamRegistrationResponseDTO>> getAllTeams() {
        return ResponseEntity.ok(
                teamRegistrationService.getAllTeams());
    }

    @PutMapping("/{teamId}/status")
    public ResponseEntity<String> updateTeamStatus(
            @PathVariable Long teamId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                teamRegistrationService.updateTeamStatus(teamId, status));
    }
}

