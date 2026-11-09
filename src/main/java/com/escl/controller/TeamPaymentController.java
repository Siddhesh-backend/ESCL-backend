
package com.escl.controller;

import com.escl.service.TeamPaymentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/team-registrations")
@RequiredArgsConstructor
public class TeamPaymentController {

    private final TeamPaymentService teamPaymentService;

    @PostMapping(
            value = "/{teamId}/payment-screenshot",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadScreenshot(
            @PathVariable Long teamId,
            @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(
                teamPaymentService.uploadScreenshot(teamId, file)
        );
    }
    @PutMapping("/{teamId}/payment-status")
    public ResponseEntity<String> updatePaymentStatus(
            @PathVariable Long teamId,
            @RequestParam String status) {

        return ResponseEntity.ok(
                teamPaymentService.updatePaymentStatus(teamId, status)
        );
    }
}

