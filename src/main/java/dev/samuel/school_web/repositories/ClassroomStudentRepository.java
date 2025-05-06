package dev.samuel.school_web.repositories;

import dev.samuel.school_web.entities.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Long> {}
