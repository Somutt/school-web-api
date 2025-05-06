package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.controllers.dto.ResponseClassroomDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.repositories.ClassroomRepository;
import dev.samuel.school_web.repositories.ProfessorRepository;
import dev.samuel.school_web.repositories.RoomRepository;
import dev.samuel.school_web.services.mappers.ClassroomMapper;
import dev.samuel.school_web.validators.ClassroomValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomService {
    private final ClassroomRepository repository;
    private final ClassroomValidator validator;
    private final ClassroomMapper mapper;
    private final ProfessorRepository professorRepository;
    private final RoomRepository roomRepository;

    public ClassroomService(ClassroomRepository repository, ClassroomValidator validator ,ClassroomMapper mapper,
                            ProfessorRepository professorRepository, RoomRepository roomRepository) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
        this.professorRepository = professorRepository;
        this.roomRepository = roomRepository;
    }

    public List<ResponseClassroomDTO> findAll() {
        List<Classroom> classrooms = repository.findAll();

        return classrooms.stream().map(mapper::toDTO).toList();
    }

    public ResponseClassroomDTO findById(String id) {
        UUID classroomId = UUID.fromString(id);
        Optional<Classroom> classroomOptional = repository.findById(classroomId);

        return classroomOptional.map(mapper::toDTO).orElse(null);
    }

    public Classroom save(RegisterClassroomDTO registerClassroomDTO) {
        Classroom classroom = mapper.toEntity(registerClassroomDTO);

        validator.validate(classroom);
        classroom = repository.save(classroom);
        return classroom;
    }

    public void delete(String id) {
        UUID classroomId = UUID.fromString(id);
        repository.deleteById(classroomId);
    }

    public ResponseClassroomDTO update(String id, RegisterClassroomDTO registerClassroomDTO) {
        UUID classroomId = UUID.fromString(id);
        Optional<Classroom> classroomOptional = repository.findById(classroomId);
        if (classroomOptional.isEmpty()) {
            return null;
        }

        Classroom classroom = classroomOptional.get();
        updateClassroom(classroom, registerClassroomDTO);

        validator.validate(classroom);
        return mapper.toDTO(repository.save(classroom));
    }

    private void updateClassroom(Classroom classroom, RegisterClassroomDTO registerClassroomDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime formatted = LocalDateTime.parse(registerClassroomDTO.schedule(), formatter);

        classroom.setCode(registerClassroomDTO.code());
        classroom.setSchedule(formatted);
        classroom.setProfessor(professorRepository.findById(registerClassroomDTO.professorId()).orElse(null));
        classroom.setRoom(roomRepository.findById(registerClassroomDTO.roomId()).orElse(null));
    }
}
