package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.*;

public record RoomDTO(
        @NotBlank @Size(max = 30, message = "Maximum name size is 30")
        String name,
        @NotNull @Min(value = 1, message = "Minimum capacity is 1") @Max(value = 1000, message = "Maximum capacity is 1000")
        Integer capacity
) {}
