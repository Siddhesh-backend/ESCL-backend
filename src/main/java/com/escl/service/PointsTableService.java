package com.escl.service;

import com.escl.dto.PointsTableRequestDTO;
import com.escl.dto.PointsTableResponseDTO;
import com.escl.exception.PointsTableAlreadyExistsException;
import com.escl.exception.ResourceNotFoundException;
import com.escl.mapper.PointsTableMapper;
import com.escl.model.PointsTable;
import com.escl.repository.PointsTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PointsTableService {

    @Autowired
    private PointsTableRepository pointsTableRepository;

    // Save
    public PointsTableResponseDTO savePointsTable(
            PointsTableRequestDTO requestDTO) {

        if (pointsTableRepository.existsBySeasonAndTeamName(
                requestDTO.getSeason(),
                requestDTO.getTeamName())) {

            throw new PointsTableAlreadyExistsException(
                    "Points Table record already exists");
        }

        PointsTable table = PointsTableMapper.toEntity(requestDTO);

        PointsTable savedTable =
                pointsTableRepository.save(table);

        return PointsTableMapper.toDTO(savedTable);
    }

    // Get All
    public List<PointsTableResponseDTO> getAllPointsTable() {

        return pointsTableRepository.findAll()
                .stream()
                .map(PointsTableMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Get By Id
    public PointsTableResponseDTO getPointsTableById(Long id) {

        PointsTable table = pointsTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Points Table Not Found with ID : " + id));

        return PointsTableMapper.toDTO(table);
    }

    // Update
    public PointsTableResponseDTO updatePointsTable(
            Long id,
            PointsTableRequestDTO requestDTO) {

        PointsTable table = pointsTableRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Points Table Not Found with ID : " + id));

        table.setSeason(requestDTO.getSeason());
        table.setPosition(requestDTO.getPosition());
        table.setTeamName(requestDTO.getTeamName());
        table.setMatchesPlayed(requestDTO.getMatchesPlayed());
        table.setWon(requestDTO.getWon());
        table.setLost(requestDTO.getLost());
        table.setTie(requestDTO.getTie());
        table.setNoResult(requestDTO.getNoResult());
        table.setNetRunRate(requestDTO.getNetRunRate());
        table.setPoints(requestDTO.getPoints());

        PointsTable updatedTable =
                pointsTableRepository.save(table);

        return PointsTableMapper.toDTO(updatedTable);
    }

    // Delete
    public String deletePointsTable(Long id) {

        if (!pointsTableRepository.existsById(id)) {

            throw new ResourceNotFoundException(
                    "Points Table Data Not Found with ID : " + id);
        }

        pointsTableRepository.deleteById(id);

        return "Points Table Deleted Successfully";
    }
}