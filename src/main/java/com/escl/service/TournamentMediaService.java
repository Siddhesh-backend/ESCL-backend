package com.escl.service;

import com.escl.dto.TournamentMediaRequestDTO;
import com.escl.dto.TournamentMediaResponseDTO;
import com.escl.mapper.TournamentMediaMapper;
import com.escl.model.TournamentMedia;
import com.escl.repository.TournamentMediaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TournamentMediaService {

    private final TournamentMediaRepository repository;

    @Transactional
    public TournamentMediaResponseDTO createMedia(TournamentMediaRequestDTO dto) {
        TournamentMedia media = TournamentMediaMapper.toEntity(dto);
        return TournamentMediaMapper.toResponse(repository.save(media));
    }

    public List<TournamentMediaResponseDTO> getAllMedia() {
        return repository.findAll()
                .stream()
                .map(TournamentMediaMapper::toResponse)
                .toList();
    }

    public TournamentMediaResponseDTO getMediaById(Long id) {
        TournamentMedia media = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tournament media not found"));

        return TournamentMediaMapper.toResponse(media);
    }

    @Transactional
    public TournamentMediaResponseDTO updateMedia(
            Long id, TournamentMediaRequestDTO dto) {

        TournamentMedia media = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tournament media not found"));

        TournamentMediaMapper.updateEntity(media, dto);

        return TournamentMediaMapper.toResponse(repository.save(media));
    }

    @Transactional
    public void deleteMedia(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Tournament media not found");
        }

        repository.deleteById(id);
    }
}