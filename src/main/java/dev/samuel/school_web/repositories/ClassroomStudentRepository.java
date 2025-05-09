package dev.samuel.school_web.repositories;

import dev.samuel.school_web.entities.ClassroomStudent;
import dev.samuel.school_web.entities.embedded_pk.ClassroomStudentPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, ClassroomStudentPK> {}
