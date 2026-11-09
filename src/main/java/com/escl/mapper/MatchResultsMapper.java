package com.escl.mapper;

import com.escl.dto.MatchResultsRequestDTO;
import com.escl.dto.MatchResultsResponseDTO;
import com.escl.model.MatchResults;

public class MatchResultsMapper {

    public static MatchResults toEntity(MatchResultsRequestDTO dto) {
        MatchResults match = new MatchResults();
        updateEntity(match, dto);
        return match;
    }

    public static void updateEntity(
            MatchResults match,
            MatchResultsRequestDTO dto) {

        match.setMatchTitle(dto.getMatchTitle());
        match.setTeam1Name(dto.getTeam1Name());
        match.setTeam2Name(dto.getTeam2Name());
        match.setTeam1Score(dto.getTeam1Score());
        match.setTeam2Score(dto.getTeam2Score());
        match.setWinner(dto.getWinner());
        match.setResultSummary(dto.getResultSummary());
        match.setMatchDate(dto.getMatchDate());
    }

    public static MatchResultsResponseDTO toResponse(MatchResults match) {
        MatchResultsResponseDTO dto = new MatchResultsResponseDTO();

        dto.setId(match.getId());
        dto.setMatchTitle(match.getMatchTitle());
        dto.setTeam1Name(match.getTeam1Name());
        dto.setTeam2Name(match.getTeam2Name());
        dto.setTeam1Score(match.getTeam1Score());
        dto.setTeam2Score(match.getTeam2Score());
        dto.setWinner(match.getWinner());
        dto.setResultSummary(match.getResultSummary());
        dto.setMatchDate(match.getMatchDate());
        dto.setCreatedAt(match.getCreatedAt());
        dto.setUpdatedAt(match.getUpdatedAt());

        return dto;
    }
}