package com.escl.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TournamentMediaResponseDTO {

    private Long id;
    private String title;
    private String description;
    private String youtubeUrl;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}