package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoomDTO(
        @NotBlank @Size(max = 30, message = "Maximum name size is 30")
        String name,
        @NotNull @Min(value = 1, message = "Minimum capacity is 1")
        Integer capacity
) {}
