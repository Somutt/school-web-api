package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record RegisterClassroomDTO(
        @NotBlank @Size(min = 5, max = 5, message = "The code size is out of bounds")
        @Pattern(regexp = "[A-Z][0-9]+", message = "The code is out of pattern")
        String code,
        @NotBlank
        String schedule,
        @NotNull
        UUID professorId,
        @NotNull
        UUID roomId
) {}
