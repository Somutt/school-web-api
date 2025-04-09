package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.mappers.StudentMapper;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.repositories.StudentRepository;
import dev.samuel.school_web.services.specifications.StudentSpecs;
import dev.samuel.school_web.validators.StudentValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository repository;
    private final StudentValidator validator;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repository,
                          StudentValidator validator,
                          @Qualifier("studentMapperImpl") StudentMapper mapper) {
        this.repository = repository;
        this.validator = validator;
        this.mapper = mapper;
    }

    public List<StudentDTO> findAll() {
        List<Student> students = repository.findAll();

        return students.stream().map(mapper::toDTO).toList();
    }

    public Page<StudentDTO> search(String name, String registry, String grade, Integer page, Integer pageSize) {
        Specification<Student> specs = Specification.where(
                (root, query, criteriaBuilder) -> criteriaBuilder.conjunction());

        if (name != null) {
            specs = specs.and(StudentSpecs.nameLike(name));
        }

        if (registry != null) {
            specs = specs.and(StudentSpecs.registryEquals(registry));
        }

        if(grade != null) {
            specs = specs.and(StudentSpecs.gradeEquals(grade));
        }

        Pageable request = PageRequest.of(page, pageSize);
        Page<Student> students = repository.findAll(specs, request);
        return students.map(mapper::toDTO);
    }

    public StudentDTO findById(String id) {
        UUID studentId = UUID.fromString(id);
        Optional<Student> studentOptional = repository.findById(studentId);

        return studentOptional.map(mapper::toDTO).orElse(null);
    }

    public Student save(StudentDTO studentDTO) {
        Student student = mapper.toEntity(studentDTO);

        validator.validate(student);
        return repository.save(student);
    }

    public void delete(String id) {
        UUID studentId = UUID.fromString(id);
        repository.deleteById(studentId);
    }

    public StudentDTO update(String id, StudentDTO studentDTO) {
        UUID studentId = UUID.fromString(id);
        Optional<Student> studentOptional = repository.findById(studentId);
        if (studentOptional.isEmpty()) {
            return null;
        }

        Student student = studentOptional.get();
        updateStudent(student, studentDTO);

        validator.validate(student);
        return mapper.toDTO(repository.save(student));
    }

    private void updateStudent(Student student, StudentDTO studentDTO) {
        student.setName(studentDTO.name());
        student.setRegistry(studentDTO.registry());
        student.setGrade(studentDTO.grade());
    }
}
