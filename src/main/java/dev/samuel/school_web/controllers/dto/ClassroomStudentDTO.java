package dev.samuel.school_web.controllers.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClassroomStudentDTO(
        @NotNull
        UUID studentId
) {}
