package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.mappers.StudentMapper;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    private final StudentMapper mapper;

    public StudentController(StudentService service, @Qualifier("studentMapperImpl") StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> index() {
        List<Student> students = service.findAll();
        List<StudentDTO> studentDTOS = students.stream().map(mapper::toDTO).toList();

        return ResponseEntity.ok(studentDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> show(@PathVariable String id) {
        UUID studentId = UUID.fromString(id);
        Optional<Student> optional = service.findById(studentId);

        if (optional.isPresent()) {
            Student student = optional.get();
            StudentDTO dto = mapper.toDTO(student);

            return ResponseEntity.ok(dto);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentDTO> store(@RequestBody @Valid StudentDTO studentDto) {
        Student student = mapper.toEntity(studentDto);
        student = service.save(student);

        URI uri = URIUtils.createHeaderLocation(student.getId());
        return ResponseEntity.created(uri).body(studentDto);
    }
}
