package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.ProfessorDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Professor;
import dev.samuel.school_web.services.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/professors")
public class ProfessorController {
    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> index() {
        List<ProfessorDTO> professors = service.findAll();

        return ResponseEntity.ok(professors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> show(@PathVariable String id) {
        ProfessorDTO professorDTO = service.findById(id);
        if (professorDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(professorDTO);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> store(@RequestBody @Valid ProfessorDTO professorDTO) {
        Professor professor = service.save(professorDTO);
        URI uri = URIUtils.createHeaderLocation(professor.getId());

        return ResponseEntity.created(uri).body(professorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        ProfessorDTO professorDTO = service.findById(id);
        if (professorDTO == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable String id, @RequestBody @Valid ProfessorDTO professorDTO) {
       professorDTO = service.update(id, professorDTO);
       if (professorDTO == null) {
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(professorDTO);
    }
}
