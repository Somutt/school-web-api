package dev.samuel.school_web.entities.embeddedpk;

import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.entities.Student;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Embeddable
public class ClassroomStudentPK {

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClassroomStudentPK that = (ClassroomStudentPK) o;
        return Objects.equals(student, that.student) && Objects.equals(classroom, that.classroom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, classroom);
    }

    @Override
    public String toString() {
        return "ClassroomStudentPK";
    }
}
