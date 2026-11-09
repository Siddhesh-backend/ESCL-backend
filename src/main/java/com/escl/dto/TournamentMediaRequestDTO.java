package com.escl.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;

@Getter
@Setter
public class TournamentMediaRequestDTO {

    @NotBlank
    private String title;

    @Size(max = 1000)
    private String description;

    @NotBlank(message = "YouTube URL is required")
    @Size(max = 1000)
    @Pattern(
            regexp = "^(https?://)?(www\\.)?(youtube\\.com|youtu\\.be)/.+$",
            message = "Please provide a valid YouTube URL"
    )
    private String youtubeUrl;

    private String category;
}