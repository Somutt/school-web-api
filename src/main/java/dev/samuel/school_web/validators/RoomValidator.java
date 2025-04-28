package dev.samuel.school_web.validators;

import dev.samuel.school_web.entities.Room;
import dev.samuel.school_web.errors.exceptions.DuplicatedRegisterException;
import dev.samuel.school_web.repositories.RoomRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoomValidator {
    private final RoomRepository repository;

    public RoomValidator(RoomRepository repository) {
        this.repository = repository;
    }

    public void validate(Room room) {
        if (existsRoomWithName(room)) {
            throw new DuplicatedRegisterException("Room with this name already exists");
        }
    }

    private boolean existsRoomWithName(Room room) {
        Optional<Room> optional = repository.findByName(room.getName());
        if (optional.isEmpty()) {
            return false;
        }

        return optional.map(Room::getId).stream().anyMatch(id -> !id.equals(room.getId()));
    }
}
