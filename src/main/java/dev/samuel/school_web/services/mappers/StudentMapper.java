package dev.samuel.school_web.services.mappers;

import dev.samuel.school_web.controllers.dto.StudentDTO;
import dev.samuel.school_web.entities.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toEntity(StudentDTO studentDTO);
    StudentDTO toDTO(Student student);
}
