package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisterClassroomDTO(
        @NotBlank @Size(min = 5, max = 5, message = "The code size is out of bounds")
        @Pattern(regexp = "[A-Z][0-9]+", message = "The code is out of pattern")
        String code,
        @NotNull
        UUID professorId,
        @NotNull
        UUID roomId
) {}
