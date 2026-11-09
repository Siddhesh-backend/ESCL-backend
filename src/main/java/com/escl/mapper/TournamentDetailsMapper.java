package com.escl.mapper;

import com.escl.dto.TournamentDetailsRequestDTO;
import com.escl.dto.TournamentDetailsResponseDTO;
import com.escl.model.TournamentDetails;

public class TournamentDetailsMapper {

    private TournamentDetailsMapper() {
    }

    public static TournamentDetails toEntity(
            TournamentDetailsRequestDTO dto) {

        TournamentDetails tournament = new TournamentDetails();

        tournament.setTournamentName(dto.getTournamentName());
        tournament.setDescription(dto.getDescription());
        tournament.setVenue(dto.getVenue());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());
        tournament.setRules(dto.getRules());

        return tournament;
    }

    public static void updateEntity(
            TournamentDetails tournament,
            TournamentDetailsRequestDTO dto) {

        tournament.setTournamentName(dto.getTournamentName());
        tournament.setDescription(dto.getDescription());
        tournament.setVenue(dto.getVenue());
        tournament.setStartDate(dto.getStartDate());
        tournament.setEndDate(dto.getEndDate());
        tournament.setRules(dto.getRules());
    }

    public static TournamentDetailsResponseDTO toResponse(
            TournamentDetails tournament) {

        TournamentDetailsResponseDTO dto =
                new TournamentDetailsResponseDTO();

        dto.setId(tournament.getId());
        dto.setTournamentName(tournament.getTournamentName());
        dto.setDescription(tournament.getDescription());
        dto.setVenue(tournament.getVenue());
        dto.setStartDate(tournament.getStartDate());
        dto.setEndDate(tournament.getEndDate());
        dto.setRules(tournament.getRules());
        dto.setCreatedAt(tournament.getCreatedAt());
        dto.setUpdatedAt(tournament.getUpdatedAt());

        return dto;
    }
}
