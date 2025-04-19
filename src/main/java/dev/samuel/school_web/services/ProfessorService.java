package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.ProfessorDTO;
import dev.samuel.school_web.repositories.ProfessorRepository;
import dev.samuel.school_web.services.mappers.ProfessorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {
    private ProfessorRepository repository;
    private ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ProfessorDTO> findAll() {
        return null;
    }

    public ProfessorDTO findById(String id) {
        return null;
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        return null;
    }

    public void delete(String id) {

    }

    public ProfessorDTO update(String id, ProfessorDTO professorDTO) {
        return null;
    }
}
