package dev.samuel.school_web.services;

import dev.samuel.school_web.controllers.dto.ClassroomStudentDTO;
import dev.samuel.school_web.entities.Classroom;
import dev.samuel.school_web.entities.ClassroomStudent;
import dev.samuel.school_web.entities.Student;
import dev.samuel.school_web.errors.exceptions.AttachResourceNotFoundException;
import dev.samuel.school_web.repositories.ClassroomRepository;
import dev.samuel.school_web.repositories.ClassroomStudentRepository;
import dev.samuel.school_web.repositories.StudentRepository;
import org.springframework.stereotype.Service;

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
        UUID classroomUUID = UUID.fromString(classroomId);
        UUID studentUUID = classroomStudentDTO.studentId();
        Classroom classroom = classroomRepository.findById(classroomUUID).orElse(null);
        Student student = studentRepository.findById(studentUUID).orElse(null);

        if (classroom == null || student == null) {
            throw new AttachResourceNotFoundException("Designated classroom or student not found");
        }

        ClassroomStudent classroomStudent = repository.save(new ClassroomStudent(classroom, student));
        classroom.getStudents().add(classroomStudent);
    }

    public void delete(String classroomId, String studentId) {}
}
