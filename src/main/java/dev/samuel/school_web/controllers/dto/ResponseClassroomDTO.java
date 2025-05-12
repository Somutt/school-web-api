package dev.samuel.school_web.controllers.dto;

import java.util.List;

public record ResponseClassroomDTO(
        String code,
        String schedule,
        ProfessorDTO professor,
        RoomDTO room,
        List<StudentDTO> students
) {}
