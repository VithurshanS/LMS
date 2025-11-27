package org.lms.EntityRelationshipTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.lms.Model.Department;
import org.lms.Model.Lecturer;
import org.lms.Model.User;
import org.lms.Model.UserRole;
import org.lms.Repository.DepartmentRepository;
import org.lms.Repository.LecturerRepository;
import org.lms.Repository.UserRepository;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

import jakarta.transaction.Transactional;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LecturerTest {
    
    @Inject
    LecturerRepository lecturerRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    DepartmentRepository departmentRepository;


    @BeforeAll
    @Transactional
    public void setup() {
        TestHelper.setupSampleUsers(userRepository);
        TestHelper.setupSampleDepartments(departmentRepository);
    }

    @Test
    @Transactional
    public void createLecturerTestvalid(){
        User foundLecturerUser = userRepository.find("username", "lecturer123").firstResult();
        Department foundCsDept = departmentRepository.find("name", "Computer Science").firstResult();
        
        Lecturer lecturer1 = new Lecturer(foundLecturerUser, foundCsDept);
        lecturerRepository.persist(lecturer1);
        lecturerRepository.flush();
        
        lecturer1 = lecturerRepository.findById(lecturer1.getId());
        assert(lecturer1.getUser().getEmail().equals("lecturer@lms.com"));
    }

    @Test
    @Transactional
    public void createLecturerWithWrongRole(){
        User foundStudentUser = userRepository.find("username", "student123").firstResult();
        Department foundCsDept = departmentRepository.find("name", "Computer Science").firstResult();
  
        
        try {
            Lecturer lecturer = new Lecturer(foundStudentUser, foundCsDept);
            assert false : "Should throw IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }

    @Test
    @Transactional
    public void createLecturerWithWrongRole2(){
        User foundAdminUser = userRepository.find("username", "admin123").firstResult();
        Department foundCsDept = departmentRepository.find("name", "Computer Science").firstResult();
  
        
        try {
            Lecturer lecturer = new Lecturer(foundAdminUser, foundCsDept);
            assert false : "Should throw IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert true;
        }
    }
}
