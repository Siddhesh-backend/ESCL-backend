
package com.escl.controller;

import com.escl.dto.AdminLoginRequestDTO;
import com.escl.dto.AdminLoginResponseDTO;
import com.escl.service.AdminAuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    public AdminAuthController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseDTO> login(
            @Valid @RequestBody AdminLoginRequestDTO requestDTO,
            HttpServletRequest request) {

        AdminLoginResponseDTO response =
                adminAuthService.login(requestDTO);

        // Ensure the authenticated session is available.
        request.getSession(true);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<AdminLoginResponseDTO> getCurrentAdmin(
            Authentication authentication) {

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                !authentication.getAuthorities().stream()
                        .anyMatch(authority ->
                                authority.getAuthority().equals("ROLE_ADMIN"))) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(
                new AdminLoginResponseDTO(
                        "Admin session active",
                        authentication.getName(),
                        "ADMIN"
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        SecurityContextHolder.clearContext();

        if (session != null) {
            session.invalidate();
        }

        return ResponseEntity.ok("Admin logged out successfully");
    }
}
