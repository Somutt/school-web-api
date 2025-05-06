package dev.samuel.school_web.validators;

import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.errors.exceptions.DuplicatedRegisterException;
import dev.samuel.school_web.errors.exceptions.UnavailableResourceException;
import dev.samuel.school_web.repositories.ClassroomRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ClassroomValidator {
    private final ClassroomRepository repository;

    public ClassroomValidator(ClassroomRepository repository) {
        this.repository = repository;
    }

    public void validate(Classroom classroom) {
        if (existsClassroomWithCode(classroom)) {
            throw new DuplicatedRegisterException("Classroom with this code already exists");
        }
        if (roomOrProfessorUnavailableForThisSchedule(classroom)) {
            throw new UnavailableResourceException("Room or professor unavailable for this schedule");
        }
    }

    private boolean existsClassroomWithCode(Classroom classroom) {
        Optional<Classroom> optional = repository.findByCode(classroom.getCode());
        if (optional.isEmpty()) {
            return false;
        }

        return optional.map(Classroom::getId).stream().anyMatch(id -> !id.equals(classroom.getId()));
    }

    private boolean roomOrProfessorUnavailableForThisSchedule(Classroom classroom) {
        List<Classroom> classrooms = repository.findAllBySchedule(classroom.getSchedule());
        for (Classroom c : classrooms) {
            if (!c.getId().equals(classroom.getId())) {
                if (c.getRoom().getId().equals(classroom.getRoom().getId()) ||
                        c.getProfessor().getId().equals(classroom.getProfessor().getId())) {
                    return true;
                }
            }
        }

        return false;
    }
}
