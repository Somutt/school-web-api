package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record StudentDTO(
        @NotBlank @Size(max = 100, message = "Maximum name size is 100")
        String name,
        @NotBlank @Size(min = 6, max = 6, message = "Registry length out of bounds")
        @Pattern(regexp = "[A-Z][A-Z][A-Z][A-Z][0-9]+", message = "Registry out of pattern")
        String registry,
        @NotBlank @Size(min = 2, max = 2, message = "Grande length out of bounds")
        @Pattern(regexp = "\\d[A-E]", message = "Grade out of pattern")
        String grade
) {}
