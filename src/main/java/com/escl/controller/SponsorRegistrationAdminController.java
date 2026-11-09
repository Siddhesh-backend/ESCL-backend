package com.escl.controller;

import com.escl.dto.SponsorRegistrationResponseDTO;
import com.escl.service.SponsorRegistrationService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sponsor-registrations")
@RequiredArgsConstructor
public class SponsorRegistrationAdminController {

    private final SponsorRegistrationService sponsorService;

    // Admin: View all sponsor registrations
    @GetMapping
    public ResponseEntity<List<SponsorRegistrationResponseDTO>> getAllSponsors() {
        return ResponseEntity.ok(sponsorService.getAllSponsors());
    }

    // Admin: Approve or reject sponsor
    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateSponsorStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return ResponseEntity.ok(
                sponsorService.updateSponsorStatus(id, status)
        );
    }
}