
package com.escl.mapper;

import com.escl.dto.TeamRegistrationRequestDTO;
import com.escl.dto.TeamRegistrationResponseDTO;
import com.escl.model.PlayerRegistration;
import com.escl.model.TeamRegistration;

import java.math.BigDecimal;
import java.util.List;

public class TeamRegistrationMapper {

    private TeamRegistrationMapper() {
    }

    public static TeamRegistration toEntity(
            TeamRegistrationRequestDTO dto,
            BigDecimal entryFee) {

        TeamRegistration team = new TeamRegistration();

        team.setTeamName(dto.getTeamName());
        team.setCollegeUniversity(dto.getCollegeUniversity());
        team.setCaptainFullName(dto.getCaptainFullName());
        team.setCaptainCollegeId(dto.getCaptainCollegeId());
        team.setCaptainEmail(dto.getCaptainEmail());
        team.setCaptainContactNumber(dto.getCaptainContactNumber());
        team.setAlternateContact(dto.getAlternateContact());
        team.setCity(dto.getCity());
        team.setEntryFee(entryFee);
        team.setPaymentMethod(dto.getPaymentMethod());
        team.setTransactionReference(dto.getTransactionReference());

        return team;
    }

    public static PlayerRegistration toPlayerEntity(
            TeamRegistrationRequestDTO.PlayerDTO dto,
            TeamRegistration team) {

        PlayerRegistration player = new PlayerRegistration();

        player.setFullName(dto.getFullName());
        player.setCollegeId(dto.getCollegeId());
        player.setJerseyNumber(dto.getJerseyNumber());
        player.setContactNumber(dto.getContactNumber());
        player.setTeamRegistration(team);

        return player;
    }

    public static TeamRegistrationResponseDTO toResponse(
            TeamRegistration team,
            List<PlayerRegistration> players) {

        TeamRegistrationResponseDTO response =
                new TeamRegistrationResponseDTO();

        response.setId(team.getId());
        response.setTeamName(team.getTeamName());
        response.setCollegeUniversity(team.getCollegeUniversity());
        response.setCaptainFullName(team.getCaptainFullName());
        response.setCaptainEmail(team.getCaptainEmail());
        response.setCity(team.getCity());
        response.setEntryFee(team.getEntryFee());
        response.setPaymentStatus(team.getPaymentStatus());
        response.setTeamStatus(team.getTeamStatus());
        response.setCreatedAt(team.getCreatedAt());

        List<TeamRegistrationResponseDTO.PlayerResponseDTO> playerResponses =
                players.stream().map(player -> {
                    TeamRegistrationResponseDTO.PlayerResponseDTO dto =
                            new TeamRegistrationResponseDTO.PlayerResponseDTO();

                    dto.setId(player.getId());
                    dto.setFullName(player.getFullName());
                    dto.setCollegeId(player.getCollegeId());
                    dto.setJerseyNumber(player.getJerseyNumber());
                    dto.setContactNumber(player.getContactNumber());

                    return dto;
                }).toList();

        response.setPlayers(playerResponses);

        return response;
    }
}

