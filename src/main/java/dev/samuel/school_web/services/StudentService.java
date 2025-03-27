package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.mappers.StudentMapper;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repository, @Qualifier("studentMapperImpl") StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<StudentDTO> findAll() {
        List<Student> students = repository.findAll();

        return students.stream().map(mapper::toDTO).toList();
    }

    public StudentDTO findById(String id) {
        UUID studentId = UUID.fromString(id);
        Optional<Student> studentOptional = repository.findById(studentId);

        return studentOptional.map(mapper::toDTO).orElse(null);
    }

    public Student save(StudentDTO studentDTO) {
        Student student = mapper.toEntity(studentDTO);
        return repository.save(student);
    }

    public void delete(String id) {
        UUID studentId = UUID.fromString(id);
        repository.deleteById(studentId);
    }
}
