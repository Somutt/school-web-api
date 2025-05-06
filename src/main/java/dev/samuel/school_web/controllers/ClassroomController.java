package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.controllers.dto.ResponseClassroomDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.services.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService service;

    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ResponseClassroomDTO>> index() {
        List<ResponseClassroomDTO> classrooms = service.findAll();

        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClassroomDTO> show(@PathVariable String id) {
        ResponseClassroomDTO responseClassroomDTO = service.findById(id);
        if (responseClassroomDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(responseClassroomDTO);
    }

    @PostMapping
    public ResponseEntity<Void> store(@RequestBody @Valid RegisterClassroomDTO registerClassroomDTO) {
        Classroom classroom = service.save(registerClassroomDTO);
        URI uri = URIUtils.createHeaderLocation(classroom.getId());

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
