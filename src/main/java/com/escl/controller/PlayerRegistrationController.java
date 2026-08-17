package com.escl.controller;

import com.escl.dto.LoginResponseDTO;
import com.escl.dto.PlayerRegistrationRequestDTO;
import com.escl.dto.PlayerRegistrationResponseDTO;
import com.escl.model.LoginRequest;
import com.escl.service.PlayerRegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import jakarta.servlet.http.HttpServletRequest;


import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerRegistrationController {

    @Autowired
    private PlayerRegistrationService playerRegistrationService;
    @Autowired
    private AuthenticationManager authenticationManager;

    // Register Player
    @PostMapping
    public ResponseEntity<PlayerRegistrationResponseDTO> registerPlayer(
            @Valid @RequestBody PlayerRegistrationRequestDTO requestDTO) {

        PlayerRegistrationResponseDTO responseDTO =
                playerRegistrationService.registerPlayer(requestDTO);

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    // Get All Players
    @GetMapping
    public ResponseEntity<List<PlayerRegistrationResponseDTO>> getAllPlayers() {

        List<PlayerRegistrationResponseDTO> players =
                playerRegistrationService.getAllPlayers();

        return ResponseEntity.ok(players);
    }

    // Get Player By ID
    @GetMapping("/{id}")
    public ResponseEntity<PlayerRegistrationResponseDTO> getPlayerById(
            @PathVariable Long id) {

        PlayerRegistrationResponseDTO player =
                playerRegistrationService.getPlayerById(id);

        return ResponseEntity.ok(player);
    }

    // Update Player
    @PutMapping("/{id}")
    public ResponseEntity<PlayerRegistrationResponseDTO> updatePlayer(
            @PathVariable Long id,
            @Valid @RequestBody PlayerRegistrationRequestDTO requestDTO) {

        PlayerRegistrationResponseDTO updatedPlayer =
                playerRegistrationService.updatePlayer(id, requestDTO);

        return ResponseEntity.ok(updatedPlayer);
    }

    // Delete Player
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlayer(
            @PathVariable Long id) {

        String message =
                playerRegistrationService.deletePlayer(id);

        return ResponseEntity.ok(message);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @Valid @RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        SecurityContextHolder.getContext()
                .setAuthentication(authentication);

        LoginResponseDTO response =
                playerRegistrationService.loginPlayer(
                        request.getEmail()
                );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<LoginResponseDTO> getCurrentUser(
            Authentication authentication) {

        return ResponseEntity.ok(
                playerRegistrationService.getCurrentUser(
                        authentication.getName()
                )
        );
    }
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            HttpServletRequest request) {

        request.getSession().invalidate();

        return ResponseEntity.ok("Logout Successful");
    }
}