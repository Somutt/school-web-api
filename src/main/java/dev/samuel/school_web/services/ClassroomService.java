package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.repositories.ClassroomRepository;
import dev.samuel.school_web.services.mappers.ClassroomMapper;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {
    private final ClassroomRepository repository;
    private final ClassroomMapper mapper;

    public ClassroomService(ClassroomRepository repository, ClassroomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Classroom save(RegisterClassroomDTO registerClassroomDTO) {
        Classroom classroom = mapper.toEntity(registerClassroomDTO);

        return repository.save(classroom);
    }
}
