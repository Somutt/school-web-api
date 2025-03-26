package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.mappers.StudentMapper;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
    }

    public Student findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    public Student save(Student student) {
        return repository.save(student);
    }
}
