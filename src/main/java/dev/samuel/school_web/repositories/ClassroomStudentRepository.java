package dev.samuel.school_web.repositories;

import dev.samuel.school_web.entities.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, UUID> {}
