package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    /*
    @GetMapping
    public ResponseEntity<List<StudentDTO>> index() {
        List<StudentDTO> studentDTOs = service.findAll();

        return ResponseEntity.ok(studentDTOs);
    }
    */

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> index(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "registry", required = false) String registry,
            @RequestParam(value = "grade", required = false) String grade,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize
    ) {
        Page<StudentDTO> studentDTOPage = service.search(name, registry, grade, page, pageSize);
        return ResponseEntity.ok(studentDTOPage);
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        StudentDTO studentDTO = service.findById(id);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> update(@PathVariable String id, @RequestBody @Valid StudentDTO studentDTO) {
        studentDTO = service.update(id, studentDTO);
        if (studentDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(studentDTO);
    }
}
