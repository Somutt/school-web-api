package dev.samuel.school_web.entities;

import dev.samuel.school_web.entities.embedded_pk.ClassroomStudentPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "classroom_student")
public class ClassroomStudent {

    @EmbeddedId
    private final ClassroomStudentPK id = new ClassroomStudentPK();

    public ClassroomStudent() {}

    public ClassroomStudent(Classroom classroom, Student student) {
        id.setClassroom(classroom);
        id.setStudent(student);
    }

    public Classroom getClassroom() {
        return id.getClassroom();
    }

    public void setClassroom(Classroom classroom) {
        id.setClassroom(classroom);
    }

    public Student getStudent() {
        return id.getStudent();
    }

    public void setStudent(Student student) {
        id.setStudent(student);
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
