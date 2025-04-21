package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.*;

public record ProfessorDTO(
        @NotBlank @Size(max = 100, message = "Maximum name size is 100")
        String name,
        @NotNull @Min(value = 18, message = "Minimum age is 18") @Max(value = 99, message = "Maximum age is 99")
        Integer age,
        @NotNull
        boolean isCoordinator
) {}
