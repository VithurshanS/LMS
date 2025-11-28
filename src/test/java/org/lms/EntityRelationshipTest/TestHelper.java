package org.lms.EntityRelationshipTest;

import org.lms.Model.Admin;
import org.lms.Model.Department;
import org.lms.Model.Enrollment;
import org.lms.Model.Module;
import org.lms.Model.Student;
import org.lms.Model.Lecturer;
import org.lms.Model.User;
import org.lms.Model.UserRole;
import org.lms.Repository.AdminRepository;
import org.lms.Repository.DepartmentRepository;
import org.lms.Repository.LecturerRepository;
import org.lms.Repository.ModuleRepository;
import org.lms.Repository.UserRepository;

public class TestHelper {
    public static User createAdminUser(UserRepository userRepository, String username, String email) {
        User existingUser = userRepository.find("username", username).firstResult();
        if (existingUser == null) {
            User user = new User(username, email, UserRole.ADMIN, username, "password");
            userRepository.persist(user);
            userRepository.flush();
            return user;
        }
        return existingUser;
    }
    public static User createLecturerUser(UserRepository userRepository, String username, String email) {
        User existingUser = userRepository.find("username", username).firstResult();
        if (existingUser == null) {
            User user = new User(username, email, UserRole.LECTURER, username, "password");
            userRepository.persist(user);
            userRepository.flush();
            return user;
        }
        return existingUser;
    }
    public static User createStudentUser(UserRepository userRepository, String username, String email) {
        User existingUser = userRepository.find("username", username).firstResult();
        if (existingUser == null) {
            User user = new User(username, email, UserRole.STUDENT, username, "password");
            userRepository.persist(user);
            userRepository.flush();
            return user;
        }
        return existingUser;
    }
    public static Department createDepartment(DepartmentRepository departmentRepository, String deptName) {
        Department existingDept = departmentRepository.find("name", deptName).firstResult();
        if (existingDept == null) {
            Department department = new Department(deptName);
            departmentRepository.persist(department);
            departmentRepository.flush();
            return department;
        }
        return existingDept;
    }
    public static Lecturer createLecturer(LecturerRepository lecturerRepository, UserRepository userRepository, DepartmentRepository departmentRepository, String username, String deptName) {
        User user = createLecturerUser(userRepository, username, username + "@lms.com");
        Department department = createDepartment(departmentRepository, deptName);
        Lecturer existingLecturer = lecturerRepository.find("user.username", username).firstResult();
        if (existingLecturer == null) {
            Lecturer lecturer = new Lecturer(user, department);
            lecturerRepository.persist(lecturer);
            lecturerRepository.flush();
            return lecturer;
        }
        return existingLecturer;
    }

    public static Student createStudent(org.lms.Repository.StudentRepository studentRepository, UserRepository userRepository, DepartmentRepository departmentRepository, String username, String deptName) {
        User user = createStudentUser(userRepository, username, username + "@lms.com");
        Department department = createDepartment(departmentRepository, deptName);
        Student existingStudent = studentRepository.find("user.username", username).firstResult();
        if (existingStudent == null) {
            Student student = new Student(user, department);
            studentRepository.persist(student);
            studentRepository.flush();
            return student;
        }
        return existingStudent;
    }

    public static Admin createAdmin(AdminRepository adminRepository, UserRepository userRepository, String username) {
        User user = createAdminUser(userRepository, username, username + "@lms.com");
        Admin existingAdmin = adminRepository.find("user.username", username).firstResult();
        if (existingAdmin == null) {
            Admin admin = new Admin(user);
            adminRepository.persist(admin);
            adminRepository.flush();
            return admin;
        }
        return existingAdmin;
    }

    public static Module createModule(ModuleRepository moduleRepository, LecturerRepository lecturerRepository, DepartmentRepository departmentRepository, UserRepository userRepository, AdminRepository adminRepository, String moduleCode, String moduleName, int enrollmentLimit, String lecturerUsername, String deptName, String adminUsername) {
        Lecturer lecturer = createLecturer(lecturerRepository, userRepository, departmentRepository, lecturerUsername, deptName);
        Department department = createDepartment(departmentRepository, deptName);
        Admin admin = createAdmin(adminRepository, userRepository, adminUsername);

        Module existingModule = moduleRepository.find("module_code", moduleCode).firstResult();
        if (existingModule == null) {
            Module module = new Module(moduleCode, moduleName, enrollmentLimit, department, lecturer, admin);
            moduleRepository.persist(module);
            moduleRepository.flush();
            return module;
        }
        return existingModule;
    }

    public static Enrollment createEnrollment(org.lms.Repository.EnrollmentRepository enrollmentRepository, ModuleRepository moduleRepository, org.lms.Repository.StudentRepository studentRepository, String moduleCode, String studentUsername) {
        Module module = moduleRepository.find("module_code", moduleCode).firstResult();
        Student student = studentRepository.find("user.username", studentUsername).firstResult();

        Enrollment existingEnrollment = enrollmentRepository.find("module.id = ?1 and student.id = ?2", module.getId(), student.getId()).firstResult();
        if (existingEnrollment == null) {
            Enrollment enrollment = new Enrollment(student, module);
            enrollmentRepository.persist(enrollment);
            enrollmentRepository.flush();
            return enrollment;
        }
        return existingEnrollment;
    }


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

    public static void setupSampleLecturers(LecturerRepository lecturerRepository, UserRepository userRepository, DepartmentRepository departmentRepository) {
        User lecturerUser = userRepository.find("username", "lecturer123").firstResult();
        Department csDept = departmentRepository.find("name", "Computer Science").firstResult();

        Lecturer lecturer = new Lecturer(lecturerUser, csDept);
        lecturerRepository.persist(lecturer);
        lecturerRepository.flush();
    }

    public static void setupSampleModules(ModuleRepository moduleRepository, LecturerRepository lecturerRepository,DepartmentRepository departmentRepository, UserRepository userRepository,AdminRepository adminRepository) {
        Lecturer lecturer = lecturerRepository.find("user.username", "lecturer123").firstResult();
        Department csDept = departmentRepository.find("name", "Computer Science").firstResult();
        Admin admin = adminRepository.find("user.username", "admin123").firstResult();

        Module module1 = new Module("MA102", "Linear Algebra",30, csDept,lecturer,admin);

        Module module2 = new Module("CS102", "Data Structures",50, csDept,lecturer,admin);

        moduleRepository.persist(module1);
        moduleRepository.persist(module2);
        moduleRepository.flush();
    }

    
}
