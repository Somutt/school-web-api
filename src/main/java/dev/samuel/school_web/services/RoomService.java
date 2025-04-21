package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.RoomDTO;
import dev.samuel.school_web.entities.Room;
import dev.samuel.school_web.repositories.RoomRepository;
import dev.samuel.school_web.services.mappers.RoomMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {
    private final RoomRepository repository;
    private final RoomMapper mapper;

    public RoomService(RoomRepository repository, RoomMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<RoomDTO> findAll() {
        List<Room> rooms = repository.findAll();

        return rooms.stream().map(mapper::toDTO).toList();
    }

    public RoomDTO findById(String id) {
        UUID roomId = UUID.fromString(id);
        Optional<Room> roomOptional = repository.findById(roomId);

        return roomOptional.map(mapper::toDTO).orElse(null);
    }

    public Room save(RoomDTO roomDTO) {
        Room room = mapper.toEntity(roomDTO);

        return repository.save(room);
    }

    public void delete(String id) {
        UUID roomId = UUID.fromString(id);
        repository.deleteById(roomId);
    }

    public RoomDTO update(String id, RoomDTO roomDTO) {
        UUID roomId = UUID.fromString(id);
        Optional<Room> roomOptional = repository.findById(roomId);
        if (roomOptional.isEmpty()) {
            return null;
        }

        Room room = roomOptional.get();
        updateRoom(room, roomDTO);

        return mapper.toDTO(repository.save(room));
    }

    public void updateRoom(Room room, RoomDTO roomDTO) {
        room.setName(roomDTO.name());
        room.setCapacity(roomDTO.capacity());
    }
}
