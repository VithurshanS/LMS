package org.lms.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.lms.Dto.UserDetailDto;
import org.lms.Dto.UserResponseDto;
import org.lms.Exceptions.ConflictException;
import org.lms.Exceptions.NotFoundException;
import org.lms.Mapper.UserMapper;
import org.lms.Model.Department;
import org.lms.Model.Student;
import org.lms.Repository.EnrollmentRepository;
import org.lms.Repository.StudentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class StudentService {
    @Inject
    StudentRepository studRepo;

    @Inject
    UserService userService;

    @Inject
    EnrollmentRepository enrollmentRepository;

    @Inject
    UserMapper userMapper;

    @Transactional
    public void createStudent(UUID userId, Department dept){
        if(studRepo.findByUserId(userId) != null){
            throw new ConflictException("User already exists as student");
        }
        Student student = new Student(userId, dept);
        studRepo.persist(student);
    }


    public UserResponseDto getStudentDetails(UUID userId){
        Student student = studRepo.findByUserId(userId);
        if(student == null){
            throw new NotFoundException("No student found with that user id");
        }
        if(student.getDepartment() == null){
            throw new NotFoundException("This student doesn't belong to any department");
        }
        UserDetailDto userDetail = userService.fetchUserDetail(student.getUserId());
        return userMapper.toUserResponseDto(student, userDetail);
    }
    



    public UserResponseDto getStudentDetails2(UUID studentId){
        Student student = studRepo.findById(studentId);
        if(student == null){
            throw new NotFoundException("No student found with that id");
        }
        if(student.getDepartment() == null){
            throw new NotFoundException("This student doesn't belong to any department");
        }
        UserDetailDto userDetail = userService.fetchUserDetail(student.getUserId());
        return userMapper.toUserResponseDto(student, userDetail);
    }
    public List<UserResponseDto> getStudentDetailsbyDepartmentId(UUID departmentId) {

        List<Student> lecturers = studRepo.findByDepartmentId(departmentId);

        List<UserResponseDto> output = new ArrayList<>();
        for (Student lecturer : lecturers) {
            UserResponseDto dto = getStudentDetails2(lecturer.getId());
            output.add(dto);
        }
        return output;
    }
    public List<UserResponseDto> getStudentsByModuleId(UUID moduleId){
        List<Student> students = enrollmentRepository.findStudentsByModuleId(moduleId);
        return students.stream()
                .map(m -> getStudentDetails2(m.getId()))
                .toList();
    }



    public List<UserResponseDto> getAllStudents2(){
        List<Student> students = studRepo.listAll();
        return students.stream()
                .map(s -> getStudentDetails2(s.getId()))
                .toList();
    }
}