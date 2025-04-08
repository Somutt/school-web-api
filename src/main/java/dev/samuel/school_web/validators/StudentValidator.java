package dev.samuel.school_web.validators;

import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.errors.exceptions.DuplicatedRegisterException;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentValidator {
    private final StudentRepository repository;

    public StudentValidator(StudentRepository repository) {
        this.repository = repository;
    }

    public void validate(Student student) {
        if (existsStudentWithRegistry(student)) {
            throw new DuplicatedRegisterException("Student with this registry already exists");
        }
    }

    private boolean existsStudentWithRegistry(Student student) {
        Optional<Student> optional = repository.findByRegistry(student.getRegistry());
        if (optional.isEmpty()) {
            return false;
        }

        return optional.map(Student::getId).stream().anyMatch(id -> !id.equals(student.getId()));
    }
}
