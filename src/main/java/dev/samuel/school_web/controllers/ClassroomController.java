package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.RegisterClassroomDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.services.ClassroomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
    private final ClassroomService service;

    public ClassroomController(ClassroomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<RegisterClassroomDTO> store(@RequestBody @Valid RegisterClassroomDTO registerClassroomDTO) {
        Classroom classroom = service.save(registerClassroomDTO);
        URI uri = URIUtils.createHeaderLocation(classroom.getId());

        return ResponseEntity.created(uri).body(registerClassroomDTO);
    }
}
