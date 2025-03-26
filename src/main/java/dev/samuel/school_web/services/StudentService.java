package dev.samuel.school_web.services;

import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Optional<Student> findById(UUID id) {
        return repository.findById(id);
    }

    public Student save(Student student) {
        return repository.save(student);
    }
}
