
package com.escl.service;

import com.escl.model.TeamRegistration;
import com.escl.repository.TeamRegistrationRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeamPaymentService {

    private final TeamRegistrationRepository teamRepository;

    private static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    private static final Set<String> ALLOWED_TYPES = Set.of(
            "image/jpeg",
            "image/png",
            "application/pdf"
    );

    private static final Path UPLOAD_DIR =
            Paths.get("uploads", "team-payments")
                    .toAbsolutePath().normalize();

    @Transactional
    public String uploadScreenshot(Long teamId, MultipartFile file) {

        TeamRegistration team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Team not found"));

        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Screenshot is required");
        }

        if (file.getSize() > MAX_FILE_SIZE) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Maximum file size is 5 MB");
        }

        String contentType = file.getContentType();

        if (contentType == null || !ALLOWED_TYPES.contains(contentType)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Only JPG, PNG and PDF are allowed");
        }

        if ("VERIFIED".equals(team.getPaymentStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Payment is already verified");
        }

        if ("APPROVED".equals(team.getTeamStatus())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Approved team cannot update payment");
        }

        String extension = switch (contentType) {
            case "image/jpeg" -> ".jpg";
            case "image/png" -> ".png";
            case "application/pdf" -> ".pdf";
            default -> throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Unsupported file type");
        };

        String fileName = UUID.randomUUID() + extension;
        Path target = UPLOAD_DIR.resolve(fileName).normalize();

        if (!target.startsWith(UPLOAD_DIR)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid file path");
        }

        try {
            Files.createDirectories(UPLOAD_DIR);
            Files.copy(file.getInputStream(), target);
        } catch (IOException exception) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Unable to save payment screenshot");
        }

        team.setPaymentScreenshotPath(target.toString());
        team.setPaymentStatus("PENDING");

        teamRepository.save(team);

        return "Payment screenshot uploaded successfully";
    }

    @Transactional
    public String verifyPayment(Long teamId, boolean approved) {

        TeamRegistration team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Team not found"));

        // Screenshot is optional.
        // Admin can verify payment after checking bank/UPI manually.

        if (approved) {
            team.setPaymentStatus("VERIFIED");
        } else {
            team.setPaymentStatus("REJECTED");
        }

        teamRepository.save(team);

        return approved
                ? "Payment verified successfully"
                : "Payment rejected successfully";
    }

    @Transactional
    public String updatePaymentStatus(Long teamId, String status) {

        if (!"VERIFIED".equals(status) && !"REJECTED".equals(status)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid payment status");
        }

        return verifyPayment(teamId, "VERIFIED".equals(status));
    }
}
