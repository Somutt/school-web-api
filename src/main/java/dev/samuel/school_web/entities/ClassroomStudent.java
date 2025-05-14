package dev.samuel.school_web.entities;

import dev.samuel.school_web.entities.embedded_pk.ClassroomStudentPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "classroom_student")
@EntityListeners(AuditingEntityListener.class)
public class ClassroomStudent {

    @EmbeddedId
    private final ClassroomStudentPK id = new ClassroomStudentPK();

    @CreatedDate
    private LocalDateTime created;

    public ClassroomStudent() {}

    public ClassroomStudent(Classroom classroom, Student student) {
        id.setClassroom(classroom);
        id.setStudent(student);
    }

    public ClassroomStudentPK getId() {
        return id;
    }

    public Classroom getClassroom() {
        return id.getClassroom();
    }

    public Student getStudent() {
        return id.getStudent();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomStudent that = (ClassroomStudent) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Classroom_Student: Classroom: " + getClassroom().getCode() + ", Student: " + getStudent().getName();
    }
}
