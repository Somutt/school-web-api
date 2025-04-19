package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProfessorDTO(
        @NotBlank @Size(max = 100, message = "Maximum name size is 100")
        String name,
        @NotNull @Size(min = 2, max = 2, message = "Invalid age value")
        Integer age,
        @NotNull
        Boolean isCoordinator
) {}
