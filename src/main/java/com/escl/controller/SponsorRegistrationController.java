package com.escl.controller;

import com.escl.dto.SponsorRegistrationRequestDTO;
import com.escl.dto.SponsorRegistrationResponseDTO;
import com.escl.service.SponsorRegistrationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.escl.dto.PublicSponsorResponseDTO;
import java.util.List;

@RestController
@RequestMapping("/sponsor-registrations")
@RequiredArgsConstructor
public class SponsorRegistrationController {

    private final SponsorRegistrationService sponsorService;

    // Public Sponsor Registration
    @PostMapping
    public ResponseEntity<SponsorRegistrationResponseDTO> registerSponsor(
            @Valid @RequestBody SponsorRegistrationRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sponsorService.registerSponsor(dto));
    }

    @GetMapping("/public")
    public List<PublicSponsorResponseDTO> getApprovedSponsors() {
        return sponsorService.getApprovedPublicSponsors();
    }
}
