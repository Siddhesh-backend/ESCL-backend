package com.escl.mapper;

import com.escl.dto.TournamentMediaRequestDTO;
import com.escl.dto.TournamentMediaResponseDTO;
import com.escl.model.TournamentMedia;

public class TournamentMediaMapper {

    public static TournamentMedia toEntity(TournamentMediaRequestDTO dto) {
        TournamentMedia media = new TournamentMedia();
        updateEntity(media, dto);
        return media;
    }

    public static void updateEntity(
            TournamentMedia media,
            TournamentMediaRequestDTO dto) {

        media.setTitle(dto.getTitle());
        media.setDescription(dto.getDescription());
        media.setYoutubeUrl(dto.getYoutubeUrl());
        media.setCategory(dto.getCategory());
    }

    public static TournamentMediaResponseDTO toResponse(TournamentMedia media) {
        TournamentMediaResponseDTO dto = new TournamentMediaResponseDTO();

        dto.setId(media.getId());
        dto.setTitle(media.getTitle());
        dto.setDescription(media.getDescription());
        dto.setYoutubeUrl(media.getYoutubeUrl());
        dto.setCategory(media.getCategory());
        dto.setCreatedAt(media.getCreatedAt());
        dto.setUpdatedAt(media.getUpdatedAt());

        return dto;
    }
}