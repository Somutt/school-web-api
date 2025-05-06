package dev.samuel.school_web.controllers;

import dev.samuel.school_web.services.ClassroomStudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom-students")
public class ClassroomStudentController {
    private final ClassroomStudentService service;

    public ClassroomStudentController(ClassroomStudentService service) {
        this.service = service;
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> attach(@PathVariable String id, @RequestBody String studentId) {
        service.insert(id, studentId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> detach(@PathVariable String id, @RequestBody String studentId) {
        return ResponseEntity.noContent().build();
    }
}
