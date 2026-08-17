package com.escl.mapper;

import com.escl.dto.PointsTableRequestDTO;
import com.escl.dto.PointsTableResponseDTO;
import com.escl.model.PointsTable;

public class PointsTableMapper {

    public static PointsTable toEntity(PointsTableRequestDTO dto) {

        PointsTable table = new PointsTable();

        table.setSeason(dto.getSeason());
        table.setPosition(dto.getPosition());
        table.setTeamName(dto.getTeamName());
        table.setMatchesPlayed(dto.getMatchesPlayed());
        table.setWon(dto.getWon());
        table.setLost(dto.getLost());
        table.setTie(dto.getTie());
        table.setNoResult(dto.getNoResult());
        table.setNetRunRate(dto.getNetRunRate());
        table.setPoints(dto.getPoints());

        return table;
    }

    public static PointsTableResponseDTO toDTO(PointsTable table) {

        PointsTableResponseDTO dto = new PointsTableResponseDTO();

        dto.setId(table.getId());
        dto.setSeason(table.getSeason());
        dto.setPosition(table.getPosition());
        dto.setTeamName(table.getTeamName());
        dto.setMatchesPlayed(table.getMatchesPlayed());
        dto.setWon(table.getWon());
        dto.setLost(table.getLost());
        dto.setTie(table.getTie());
        dto.setNoResult(table.getNoResult());
        dto.setNetRunRate(table.getNetRunRate());
        dto.setPoints(table.getPoints());

        return dto;
    }
}