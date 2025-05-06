package dev.samuel.school_web.repositories;

import dev.samuel.school_web.entities.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, UUID> {
    Optional<Classroom> findByCode(String code);
    List<Classroom> findAllBySchedule(LocalDateTime schedule);
}
