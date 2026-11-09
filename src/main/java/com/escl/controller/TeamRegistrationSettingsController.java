
package com.escl.controller;

import com.escl.service.TeamRegistrationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/admin/team-registration-settings")
@RequiredArgsConstructor
public class TeamRegistrationSettingsController {

    private final TeamRegistrationService teamRegistrationService;

    // Get Current Entry Fee
    @GetMapping("/entry-fee")
    public ResponseEntity<Map<String, BigDecimal>> getEntryFee() {

        BigDecimal currentFee = teamRegistrationService.getEntryFee();

        return ResponseEntity.ok(
                Map.of("entryFee", currentFee)
        );
    }

    // Update Entry Fee
    @PutMapping("/entry-fee")
    public ResponseEntity<Map<String, BigDecimal>> updateEntryFee(
            @RequestBody Map<String, BigDecimal> request) {

        BigDecimal updatedFee = teamRegistrationService.updateEntryFee(
                request.get("entryFee"));

        return ResponseEntity.ok(
                Map.of("entryFee", updatedFee)
        );
    }
}
