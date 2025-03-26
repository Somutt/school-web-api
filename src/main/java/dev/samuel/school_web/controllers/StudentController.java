package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.mappers.StudentMapper;
import dev.samuel.school_web.controllers.utils.UriUtils;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;
    private final StudentMapper mapper;

    public StudentController(StudentService service, @Qualifier("studentMapperImpl") StudentMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<StudentDTO> store(@RequestBody @Valid StudentDTO studentDto) {
        Student student = mapper.toEntity(studentDto);
        student = service.save(student);

        URI uri = UriUtils.createHeaderLocation(student.getId());
        return ResponseEntity.created(uri).body(studentDto);
    }
}
