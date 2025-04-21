package dev.samuel.school_web.services.mappers;

import dev.samuel.school_web.controllers.dto.RoomDTO;
import dev.samuel.school_web.entities.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    Room toEntity(RoomDTO roomDTO);
    RoomDTO toDTO(Room room);
}
