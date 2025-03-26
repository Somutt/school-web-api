package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StudentDTO(
        @NotBlank @Size(max = 100, message = "Maximum name size is 100")
        String name,
        @NotBlank @Size(min = 6, max = 6, message = "Registry out of format")
        String registry,
        @NotBlank @Size(min = 2, max = 2, message = "Grande out of format")
        String grade
) {}
