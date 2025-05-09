package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.ClassroomStudentDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.entities.ClassroomStudent;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.entities.embedded_pk.ClassroomStudentPK;
import dev.samuel.school_web.errors.exceptions.AttachResourceNotFoundException;
import dev.samuel.school_web.repositories.ClassroomRepository;
import dev.samuel.school_web.repositories.ClassroomStudentRepository;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClassroomStudentService {
    private final ClassroomStudentRepository repository;
    private final ClassroomRepository classroomRepository;
    private final StudentRepository studentRepository;

    public ClassroomStudentService(ClassroomStudentRepository repository, ClassroomRepository classroomRepository,
                                   StudentRepository studentRepository) {
        this.repository = repository;
        this.classroomRepository = classroomRepository;
        this.studentRepository = studentRepository;
    }

    public void insert(String classroomId, ClassroomStudentDTO classroomStudentDTO) {
        Classroom classroom = findClassroom(classroomId);
        Student student = findStudent(classroomStudentDTO);
        if (classroom == null || student == null) {
            throw new AttachResourceNotFoundException("Designated classroom or student not found");
        }

        ClassroomStudent classroomStudent = repository.save(new ClassroomStudent(classroom, student));
        classroom.getStudents().add(classroomStudent);
    }

    public void delete(String classroomId, ClassroomStudentDTO classroomStudentDTO) {
        Classroom classroom = findClassroom(classroomId);
        Student student = findStudent(classroomStudentDTO);
        if (classroom == null || student == null) {
            throw new AttachResourceNotFoundException("Designated classroom or student not found");
        }

        ClassroomStudentPK classroomStudentPK = new ClassroomStudentPK(classroom, student);
        Optional<ClassroomStudent> classroomStudent = repository.findById(classroomStudentPK);
        if (classroomStudent.isEmpty()) {
            throw new AttachResourceNotFoundException("Student not registered in this classroom");
        }

        classroom.getStudents().remove(classroomStudent.get());
        repository.deleteById(classroomStudentPK);
    }

    private Classroom findClassroom(String classroomId) {
        UUID classroomUUID = UUID.fromString(classroomId);
        return classroomRepository.findById(classroomUUID).orElse(null);
    }

    private Student findStudent(ClassroomStudentDTO classroomStudentDTO) {
        UUID studentUUID = classroomStudentDTO.studentId();
        return studentRepository.findById(studentUUID).orElse(null);
    }
}
