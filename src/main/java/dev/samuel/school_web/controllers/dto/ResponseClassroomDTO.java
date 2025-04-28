package dev.samuel.school_web.controllers.dto;

public record ResponseClassroomDTO(
        String code,
        String schedule,
        ProfessorDTO professor,
        RoomDTO room
) {}
