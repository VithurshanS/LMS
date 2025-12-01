package org.lms.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lms.Model.Department;
import org.lms.Model.Enrollment;
import org.lms.Model.Student;
import org.lms.Repository.StudentRepository;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class StudentService {
    @Inject
    StudentRepository studRepo;

    public void createStudent(UUID userId, Department dept){
        studRepo.persist(new Student(userId,dept));
    }

    public List<Enrollment> getEnrollments(UUID studentId){
        return studRepo.findById(studentId).getStudentEnrollments();
    }
}
