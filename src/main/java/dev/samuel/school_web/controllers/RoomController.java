package dev.samuel.school_web.controllers;

import dev.samuel.school_web.controllers.dto.RoomDTO;
import dev.samuel.school_web.controllers.utils.URIUtils;
import dev.samuel.school_web.entities.Room;
import dev.samuel.school_web.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoomDTO>> index() {
        List<RoomDTO> rooms = service.findAll();

        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> show(@PathVariable String id) {
        RoomDTO roomDTO = service.findById(id);
        if (roomDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(roomDTO);
    }

    @PostMapping
    public ResponseEntity<RoomDTO> store(@RequestBody @Valid RoomDTO roomDTO) {
        Room room = service.save(roomDTO);
        URI uri = URIUtils.createHeaderLocation(room.getId());

        return ResponseEntity.created(uri).body(roomDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable String id) {
        RoomDTO roomDTO = service.findById(id);
        if (roomDTO == null) {
            return ResponseEntity.notFound().build();
        }

        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable String id, @RequestBody @Valid RoomDTO roomDTO) {
        roomDTO = service.update(id, roomDTO);
        if (roomDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(roomDTO);
    }
}
