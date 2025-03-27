package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> index() {
        List<StudentDTO> studentDTOs = service.findAll();

        return ResponseEntity.ok(studentDTOs);
    }

    //treat IllegalArgumentException for Ids not on UUIDs format
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> show(@PathVariable String id) {
        StudentDTO studentDTO = service.findById(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> store(@RequestBody @Valid StudentDTO studentDTO) {
        Student student = service.save(studentDTO);
        URI uri = URIUtils.createHeaderLocation(student.getId());

        return ResponseEntity.created(uri).body(studentDTO);
    }

    //treat IllegalArgumentException for Ids not on UUIDs format
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        StudentDTO studentDTO = service.findById(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
