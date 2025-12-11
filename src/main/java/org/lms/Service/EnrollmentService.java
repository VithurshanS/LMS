package org.lms.Service;

import java.util.UUID;

import org.lms.Exceptions.BusinessException;
import org.lms.Exceptions.ConflictException;
import org.lms.Exceptions.NotFoundException;
import org.lms.Model.Enrollment;
import org.lms.Model.Module;
import org.lms.Model.Student;
import org.lms.Repository.EnrollmentRepository;
import org.lms.Repository.ModuleRepository;
import org.lms.Repository.StudentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class EnrollmentService {

    @Inject
    EnrollmentRepository enrollmentRepo;

    @Inject
    StudentRepository studentRepo;

    @Inject
    ModuleRepository moduleRepo;

    @Transactional
    public Enrollment enrollStudent(UUID studentId, UUID moduleId) {
        Student student = studentRepo.findById(studentId);
        if (student == null) {
            throw new NotFoundException("Student not found with ID: " + studentId);
        }

        Module module = moduleRepo.findById(moduleId);
        if (module == null) {
            throw new NotFoundException("Module not found with ID: " + moduleId);
        }

        long existingCount = enrollmentRepo.count("student.id = ?1 and module.id = ?2", studentId, moduleId);
        if (existingCount > 0) {
            throw new ConflictException("Student is already enrolled in this module.");
        }

        long currentEnrollmentCount = enrollmentRepo.count("module.id = ?1", moduleId);
        if (currentEnrollmentCount >= module.getLimit()) {
            throw new BusinessException("Module is full. Enrollment limit reached (" + module.getLimit() + ").");
        }
        if(student.getDepartment()!=null && module.getDepartment().getId() != student.getDepartment().getId()){
            throw  new BusinessException("student cant enroll to a different department module");
        }
        if (student.getDepartment() == null){
            throw new NotFoundException("department not found");
        }

        Enrollment enrollment = new Enrollment(student, module);
        enrollmentRepo.persist(enrollment);

        return enrollment;
    }


    @Transactional
    public void unenrollStudent(UUID enrollmentId) {
        Enrollment enrollment = enrollmentRepo.findById(enrollmentId);
        if (enrollment == null) {
            throw new NotFoundException("Enrollment not found");
        }
        enrollmentRepo.delete(enrollment);
    }

    @Transactional
    public void unenrollStudent(UUID studentId, UUID moduleId) {
        long deletedCount = enrollmentRepo.delete("student.id = ?1 and module.id = ?2", studentId, moduleId);
        if (deletedCount == 0) {
            throw new NotFoundException("Enrollment record not found for this student and module");
        }
    }
}