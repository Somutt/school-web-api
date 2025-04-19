package dev.samuel.school_web.services.mappers;

import dev.samuel.school_web.controllers.dto.ProfessorDTO;
import dev.samuel.school_web.entities.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {
    Professor toEntity(ProfessorDTO professorDTO);
    ProfessorDTO toDTO(Professor professor);
}
