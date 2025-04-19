package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.ProfessorDTO;
import dev.samuel.school_web.entities.Professor;
import dev.samuel.school_web.repositories.ProfessorRepository;
import dev.samuel.school_web.services.mappers.ProfessorMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfessorService {
    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessorDTO> findAll() {
        List<Professor> professors = repository.findAll();

        return professors.stream().map(mapper::toDTO).toList();
    }

    public ProfessorDTO findById(String id) {
        UUID professorId = UUID.fromString(id);
        Optional<Professor> professorOptional = repository.findById(professorId);

        return professorOptional.map(mapper::toDTO).orElse(null);
    }

    public Professor save(ProfessorDTO professorDTO) {
        Professor professor = mapper.toEntity(professorDTO);

        return repository.save(professor);
    }

    public void delete(String id) {
        UUID professorId = UUID.fromString(id);
        repository.deleteById(professorId);
    }

    public ProfessorDTO update(String id, ProfessorDTO professorDTO) {
        UUID professorId = UUID.fromString(id);
        Optional<Professor> professorOptional = repository.findById(professorId);
        if (professorOptional.isEmpty()) {
            return null;
        }

        Professor professor = professorOptional.get();
        updateProfessor(professor, professorDTO);

        return mapper.toDTO(repository.save(professor));
    }

    private void updateProfessor(Professor professor, ProfessorDTO professorDTO) {
        professor.setName(professorDTO.name());
        professor.setAge(professorDTO.age());
        professor.setCoordinator(professorDTO.isCoordinator());
    }
}
