package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.controllers.dto.ResponseClassroomDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.repositories.ClassroomRepository;
import dev.samuel.school_web.services.mappers.ClassroomMapper;
import dev.samuel.school_web.validators.ClassroomValidator;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomService {
    private final ClassroomRepository repository;
    private final ClassroomValidator validator;
    private final ClassroomMapper mapper;

    public ClassroomService(ClassroomRepository repository, ClassroomValidator validator ,ClassroomMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
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
}
