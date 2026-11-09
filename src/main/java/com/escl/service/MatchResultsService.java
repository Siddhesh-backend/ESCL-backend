
package com.escl.service;

import com.escl.dto.MatchResultsRequestDTO;
import com.escl.dto.MatchResultsResponseDTO;
import com.escl.mapper.MatchResultsMapper;
import com.escl.model.MatchResults;
import com.escl.repository.MatchResultsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchResultsService {

    private final MatchResultsRepository repository;

    @Transactional
    public MatchResultsResponseDTO createMatchResult(
            MatchResultsRequestDTO dto) {

        validateWinner(dto);

        MatchResults match = MatchResultsMapper.toEntity(dto);

        return MatchResultsMapper.toResponse(repository.save(match));
    }

    public List<MatchResultsResponseDTO> getAllMatchResults() {
        return repository.findAll()
                .stream()
                .map(MatchResultsMapper::toResponse)
                .toList();
    }

    public MatchResultsResponseDTO getMatchResultById(Long id) {

        MatchResults match = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Match result not found"));

        return MatchResultsMapper.toResponse(match);
    }

    @Transactional
    public MatchResultsResponseDTO updateMatchResult(
            Long id, MatchResultsRequestDTO dto) {

        MatchResults match = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Match result not found"));

        validateWinner(dto);

        MatchResultsMapper.updateEntity(match, dto);

        return MatchResultsMapper.toResponse(repository.save(match));
    }

    @Transactional
    public void deleteMatchResult(Long id) {

        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Match result not found");
        }

        repository.deleteById(id);
    }

    private void validateWinner(MatchResultsRequestDTO dto) {

        if (dto.getWinner() == null || dto.getWinner().isBlank()) {
            return;
        }

        String winner = dto.getWinner().trim();

        boolean validWinner =
                (dto.getTeam1Name() != null &&
                        winner.equalsIgnoreCase(dto.getTeam1Name())) ||
                        (dto.getTeam2Name() != null &&
                                winner.equalsIgnoreCase(dto.getTeam2Name())) ||
                        winner.equalsIgnoreCase("DRAW") ||
                        winner.equalsIgnoreCase("TIE");

        if (!validWinner) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Winner must be one of the participating teams or DRAW/TIE");
        }
    }
}
