package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisterClassroomDTO(
        @NotBlank @Size(max = 5, message = "Maximum code size is 5")
        String code,
        @NotNull
        UUID professorId,
        @NotNull
        UUID roomId
) {}
