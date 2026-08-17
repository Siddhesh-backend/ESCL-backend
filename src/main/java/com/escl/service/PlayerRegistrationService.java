package com.escl.service;

import com.escl.dto.LoginResponseDTO;
import com.escl.dto.PlayerRegistrationRequestDTO;
import com.escl.dto.PlayerRegistrationResponseDTO;
import com.escl.exception.EmailAlreadyExistsException;
import com.escl.exception.InvalidCredentialsException;
import com.escl.exception.ResourceNotFoundException;
import com.escl.mapper.PlayerRegistrationMapper;
import com.escl.model.PlayerRegistration;
import com.escl.repository.PlayerRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerRegistrationService {

    @Autowired
    private PlayerRegistrationRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Register Player
    public PlayerRegistrationResponseDTO registerPlayer(PlayerRegistrationRequestDTO requestDTO) {

        if (repo.existsByEmail(requestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        PlayerRegistration player =
                PlayerRegistrationMapper.toEntity(requestDTO);

        player.setRole("PLAYER");
        player.setPassword(passwordEncoder.encode(player.getPassword()));

        PlayerRegistration savedPlayer = repo.save(player);

        return PlayerRegistrationMapper.toDTO(savedPlayer);
    }

    // Get All Players
    public List<PlayerRegistrationResponseDTO> getAllPlayers() {

        return repo.findAll()
                .stream()
                .map(PlayerRegistrationMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get Player By id
    public PlayerRegistrationResponseDTO getPlayerById(Long id) {

        PlayerRegistration player = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player Not Found with ID : " + id));

        return PlayerRegistrationMapper.toDTO(player);
    }

    // Update Player
    public PlayerRegistrationResponseDTO updatePlayer(Long id,
                                                      PlayerRegistrationRequestDTO requestDTO) {

        PlayerRegistration oldPlayer = repo.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Player Not Found with ID : " + id));

        oldPlayer.setFullName(requestDTO.getFullName());
        oldPlayer.setCollegeName(requestDTO.getCollegeName());
        oldPlayer.setBranch(requestDTO.getBranch());
        oldPlayer.setYear(requestDTO.getYear());
        oldPlayer.setDob(requestDTO.getDob());
        oldPlayer.setPosition(requestDTO.getPosition());
        oldPlayer.setExperience(requestDTO.getExperience());
        oldPlayer.setCricheroesUrl(requestDTO.getCricheroesUrl());
        oldPlayer.setBasePrice(requestDTO.getBasePrice());
        oldPlayer.setEmail(requestDTO.getEmail());
        oldPlayer.setPaymentMethod(requestDTO.getPaymentMethod());

        if (!requestDTO.getPassword().isBlank()) {
            oldPlayer.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        }

        PlayerRegistration updatedPlayer = repo.save(oldPlayer);

        return PlayerRegistrationMapper.toDTO(updatedPlayer);
    }

    // Delete Player
    public String deletePlayer(Long id) {

        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Player Not Found with ID : " + id);
        }

        repo.deleteById(id);

        return "Player Deleted Successfully";
    }

    // Login
    public LoginResponseDTO loginPlayer(String email) {

        Optional<PlayerRegistration> player = repo.findByEmail(email);

        if (player.isPresent()) {

            return new LoginResponseDTO(
                    "Login Successful",
                    player.get().getEmail(),
                    player.get().getRole()
            );
        }

        throw new InvalidCredentialsException(
                "Invalid Email or Password"
        );

    }

    public LoginResponseDTO getCurrentUser(String email) {

        PlayerRegistration player = repo.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Player Not Found"
                        ));

        return new LoginResponseDTO(
                "Session Active",
                player.getEmail(),
                player.getRole()
        );
    }
}