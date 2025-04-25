package dev.samuel.school_web.controllers.dto;

public record ResponseClassroomDTO(
        String code,
        ProfessorDTO professor,
        RoomDTO room
) {}
