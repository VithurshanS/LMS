package org.lms.EntityRelationshipTest;

import org.lms.Model.Department;
import org.lms.Model.User;
import org.lms.Model.UserRole;
import org.lms.Repository.DepartmentRepository;
import org.lms.Repository.LecturerRepository;
import org.lms.Repository.UserRepository;

import jakarta.inject.Inject;

public class TestHelper {


    public static void setupSampleUsers(UserRepository userRepository) {
        User adminUser = new User("Admin User", "admin@lms.com", UserRole.ADMIN, "admin123", "password123");
        User lecturerUser = new User("Lecturer User", "lecturer@lms.com", UserRole.LECTURER, "lecturer123", "password123");
        User studentUser = new User("Student User", "student@lms.com", UserRole.STUDENT, "student123", "password123");

        userRepository.persist(adminUser);
        userRepository.persist(lecturerUser);
        userRepository.persist(studentUser);
        userRepository.flush();
    }

    public static void setupSampleDepartments(DepartmentRepository departmentRepository) {
        Department csDept = new Department("Computer Science");
        Department mathDept = new Department("Mathematics");
        Department phyDept = new Department("Physics");

        departmentRepository.persist(csDept);
        departmentRepository.persist(mathDept);
        departmentRepository.persist(phyDept);
        departmentRepository.flush();
    }

    
}
