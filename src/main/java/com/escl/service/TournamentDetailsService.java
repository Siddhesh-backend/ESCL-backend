package com.escl.service;

import com.escl.dto.TournamentDetailsRequestDTO;
import com.escl.dto.TournamentDetailsResponseDTO;
import com.escl.mapper.TournamentDetailsMapper;
import com.escl.model.TournamentDetails;
import com.escl.repository.TournamentDetailsRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentDetailsService {

    private final TournamentDetailsRepository repository;

    @Transactional
    public TournamentDetailsResponseDTO createTournament(
            TournamentDetailsRequestDTO dto) {

        validateDates(dto);

        TournamentDetails tournament =
                TournamentDetailsMapper.toEntity(dto);

        TournamentDetails saved = repository.save(tournament);

        return TournamentDetailsMapper.toResponse(saved);
    }

    @Transactional(readOnly = true)
    public List<TournamentDetailsResponseDTO> getAllTournaments() {

        return repository.findAll()
                .stream()
                .map(TournamentDetailsMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public TournamentDetailsResponseDTO getTournamentById(Long id) {

        TournamentDetails tournament = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tournament not found."));

        return TournamentDetailsMapper.toResponse(tournament);
    }

    @Transactional
    public TournamentDetailsResponseDTO updateTournament(
            Long id,
            TournamentDetailsRequestDTO dto) {

        validateDates(dto);

        TournamentDetails tournament = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tournament not found."));

        TournamentDetailsMapper.updateEntity(tournament, dto);

        return TournamentDetailsMapper.toResponse(
                repository.save(tournament));
    }

    @Transactional
    public String deleteTournament(Long id) {

        TournamentDetails tournament = repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Tournament not found."));

        repository.delete(tournament);

        return "Tournament deleted successfully.";
    }

    private void validateDates(TournamentDetailsRequestDTO dto) {

        if (dto.getStartDate() != null
                && dto.getEndDate() != null
                && dto.getEndDate().isBefore(dto.getStartDate())) {

            throw new IllegalArgumentException(
                    "End date cannot be before start date.");
        }
    }
}