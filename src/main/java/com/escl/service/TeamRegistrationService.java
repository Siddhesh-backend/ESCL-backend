
package com.escl.service;

import com.escl.dto.TeamRegistrationRequestDTO;
import com.escl.dto.TeamRegistrationResponseDTO;
import com.escl.dto.PublicTeamResponseDTO;
import com.escl.mapper.TeamRegistrationMapper;
import com.escl.model.PlayerRegistration;
import com.escl.model.TeamRegistration;
import com.escl.model.TeamRegistrationSettings;
import com.escl.repository.PlayerRegistrationRepository;
import com.escl.repository.TeamRegistrationRepository;
import com.escl.repository.TeamRegistrationSettingsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamRegistrationService {

    private final TeamRegistrationRepository teamRepository;
    private final TeamRegistrationSettingsRepository settingsRepository;
    private final PlayerRegistrationRepository playerRepository;

    @Transactional
    public TeamRegistrationResponseDTO registerTeam(
            TeamRegistrationRequestDTO dto) {

        validateTeam(dto);

        TeamRegistrationSettings settings = settingsRepository.findById(1L)
                .orElseGet(() -> {
                    TeamRegistrationSettings newSettings =
                            new TeamRegistrationSettings();
                    newSettings.setId(1L);
                    newSettings.setEntryFee(new BigDecimal("2500.00"));
                    return settingsRepository.save(newSettings);
                });

        TeamRegistration team = TeamRegistrationMapper.toEntity(
                dto, settings.getEntryFee());

        team.setPaymentStatus("PENDING");
        team.setTeamStatus("PENDING");

        TeamRegistration savedTeam = teamRepository.save(team);

        List<PlayerRegistration> players = dto.getPlayers()
                .stream()
                .map(playerDTO -> {
                    PlayerRegistration player =
                            TeamRegistrationMapper.toPlayerEntity(
                                    playerDTO, savedTeam);

                    player.setCollegeName(dto.getCollegeUniversity());
                    player.setRole("PLAYER");
                    return player;
                })
                .toList();

        List<PlayerRegistration> savedPlayers =
                playerRepository.saveAll(players);

        return TeamRegistrationMapper.toResponse(
                savedTeam, savedPlayers);
    }
    @Transactional(readOnly = true)
    public BigDecimal getEntryFee() {

        return settingsRepository.findById(1L)
                .map(TeamRegistrationSettings::getEntryFee)
                .orElse(new BigDecimal("2500.00"));
    }
    @Transactional
    public BigDecimal updateEntryFee(BigDecimal newFee) {

        if (newFee == null || newFee.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Entry fee must be greater than zero.");
        }

        TeamRegistrationSettings settings = settingsRepository.findById(1L)
                .orElseGet(() -> {
                    TeamRegistrationSettings newSettings =
                            new TeamRegistrationSettings();
                    newSettings.setId(1L);
                    newSettings.setEntryFee(new BigDecimal("2500.00"));
                    return newSettings;
                });

        settings.setEntryFee(newFee);
        settingsRepository.save(settings);

        return settings.getEntryFee();
    }

    private void validateTeam(TeamRegistrationRequestDTO dto) {

        if (dto.getPlayers() == null
                || dto.getPlayers().isEmpty()
                || dto.getPlayers().size() > 15) {
            throw new IllegalArgumentException(
                    "Team must contain between 1 and 15 players.");
        }

        String captainCollegeId =
                normalize(dto.getCaptainCollegeId());

        long captainCount = dto.getPlayers().stream()
                .filter(player -> normalize(player.getCollegeId())
                        .equals(captainCollegeId))
                .count();

        if (captainCount != 1) {
            throw new IllegalArgumentException(
                    "Captain must appear exactly once in the player roster.");
        }

        Set<String> collegeIds = new HashSet<>();

        for (TeamRegistrationRequestDTO.PlayerDTO player
                : dto.getPlayers()) {

            if (!collegeIds.add(normalize(player.getCollegeId()))) {
                throw new IllegalArgumentException(
                        "Duplicate player College ID: "
                                + player.getCollegeId());
            }
        }

        String paymentMethod = normalize(dto.getPaymentMethod());

        if (!Set.of("UPI", "BANK_TRANSFER", "OTHER")
                .contains(paymentMethod)) {
            throw new IllegalArgumentException(
                    "Invalid payment method.");
        }
    }

    private String normalize(String value) {
        return value == null
                ? ""
                : value.trim().toUpperCase(Locale.ROOT);
    }
    @Transactional(readOnly = true)
    public List<TeamRegistrationResponseDTO> getAllTeams() {

        return teamRepository.findAll().stream()
                .map(team -> TeamRegistrationMapper.toResponse(
                        team,
                        playerRepository.findAllByTeamRegistrationId(team.getId())
                ))
                .toList();
    }
    @Transactional
    public String updateTeamStatus(Long teamId, String status) {

        if (!"APPROVED".equals(status) && !"REJECTED".equals(status)) {
            throw new IllegalArgumentException("Invalid team status.");
        }

        TeamRegistration team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found."));

        if ("APPROVED".equals(status)
                && !"VERIFIED".equals(team.getPaymentStatus())) {
            throw new IllegalArgumentException(
                    "Payment must be verified before team approval.");
        }

        team.setTeamStatus(status);
        teamRepository.save(team);

        return "Team " + status.toLowerCase() + " successfully.";
    }
    public List<PublicTeamResponseDTO> getApprovedPublicTeams() {

        return teamRepository
                .findAllByTeamStatusIgnoreCaseOrderByCreatedAtDesc("APPROVED")
                .stream()
                .map(team -> new PublicTeamResponseDTO(
                        team.getId(),
                        team.getTeamName(),
                        team.getCollegeUniversity(),
                        team.getCity(),
                        team.getCreatedAt()
                ))
                .toList();
    }
}
