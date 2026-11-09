
package com.escl.controller;

import com.escl.dto.TeamRegistrationRequestDTO;
import com.escl.dto.TeamRegistrationResponseDTO;
import com.escl.service.TeamRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.escl.dto.PublicTeamResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/team-registrations")
@RequiredArgsConstructor
public class TeamRegistrationController {

    private final TeamRegistrationService teamRegistrationService;

    @PostMapping
    public ResponseEntity<TeamRegistrationResponseDTO> registerTeam(
            @Valid @RequestBody TeamRegistrationRequestDTO requestDTO) {

        TeamRegistrationResponseDTO response =
                teamRegistrationService.registerTeam(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/public")
    public List<PublicTeamResponseDTO> getApprovedTeams() {
        return teamRegistrationService.getApprovedPublicTeams();
    }
}
