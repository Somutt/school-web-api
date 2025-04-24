package dev.samuel.school_web.services.mappers;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.repositories.ProfessorRepository;
import dev.samuel.school_web.repositories.RoomRepository;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {
    private final ProfessorRepository professorRepository;
    private final RoomRepository roomRepository;

    public ClassroomMapper(ProfessorRepository professorRepository, RoomRepository roomRepository) {
        this.professorRepository = professorRepository;
        this.roomRepository = roomRepository;
    }

    public Classroom toEntity(RegisterClassroomDTO registerClassroomDTO) {
        if (registerClassroomDTO == null) {
            return null;
        }
        Classroom classroom = new Classroom();
        classroom.setCode(registerClassroomDTO.code());
        classroom.setProfessor(professorRepository.findById(registerClassroomDTO.professorId()).orElse(null));
        classroom.setRoom(roomRepository.findById(registerClassroomDTO.roomId()).orElse(null));
        return classroom;
    }
}
