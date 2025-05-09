package dev.samuel.school_web.services.mappers;

import dev.samuel.school_web.controllers.dto.ProfessorDTO;
import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.controllers.dto.ResponseClassroomDTO;
import dev.samuel.school_web.controllers.dto.RoomDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.repositories.ProfessorRepository;
import dev.samuel.school_web.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ClassroomMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final ProfessorRepository professorRepository;
    private final RoomRepository roomRepository;
    private final ProfessorMapper professorMapper;
    private final RoomMapper roomMapper;

    public ClassroomMapper(ProfessorRepository professorRepository,
                           RoomRepository roomRepository,
                           ProfessorMapper professorMapper,
                           RoomMapper roomMapper) {
        this.professorRepository = professorRepository;
        this.roomRepository = roomRepository;
        this.professorMapper = professorMapper;
        this.roomMapper = roomMapper;
    }

    public Classroom toEntity(RegisterClassroomDTO registerClassroomDTO) {
        if (registerClassroomDTO == null) {
            return null;
        }
        LocalDateTime formatted = LocalDateTime.parse(registerClassroomDTO.schedule(), formatter);
        Classroom classroom = new Classroom();

        classroom.setCode(registerClassroomDTO.code());
        classroom.setSchedule(formatted);
        classroom.setProfessor(professorRepository.findById(registerClassroomDTO.professorId()).orElse(null));
        classroom.setRoom(roomRepository.findById(registerClassroomDTO.roomId()).orElse(null));

        return classroom;
    }

    public ResponseClassroomDTO toDTO(Classroom classroom) {
        if (classroom == null) {
            return null;
        }
        String code = classroom.getCode();
        String schedule = classroom.getSchedule().format(formatter);
        ProfessorDTO professorDTO = this.professorMapper.toDTO(classroom.getProfessor());
        RoomDTO roomDTO = this.roomMapper.toDTO(classroom.getRoom());

        return new ResponseClassroomDTO(code,schedule ,professorDTO, roomDTO);
    }
}
