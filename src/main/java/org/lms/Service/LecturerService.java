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
import org.lms.Model.Lecturer;
import org.lms.Repository.LecturerRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class LecturerService {
    @Inject
    LecturerRepository lectRepo;

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @Transactional
    public void createLecturer(UUID userId, Department dept){
        if(lectRepo.findByUserId(userId) != null){
            throw new ConflictException("user already exists as lecturer");
        }
        Lecturer lecturer = new Lecturer(userId, dept);
        lectRepo.persist(lecturer);
    }


    public UserResponseDto getLecturerDetails(UUID userId){
        Lecturer lect = lectRepo.findByUserId(userId);
        if(lect==null){
            throw new NotFoundException("no lecturer found on that user id");
        }
        if(lect.getDepartment()==null){
            throw new NotFoundException("this lecturer doesnt belongs to any department");
        }
        UserDetailDto userDetail = userService.fetchUserDetail(lect.getUserId());
        return userMapper.toUserResponseDto(lect, userDetail);
    }

    public UserResponseDto getLecturerDetails2(UUID lecturerId){
        Lecturer lect = lectRepo.findById(lecturerId);
        if(lect==null){
            throw new NotFoundException("no lecturer found on that user id");
        }
        if(lect.getDepartment()==null){
            throw new NotFoundException("this lecturer doesnt belongs to any department");
        }
        UserDetailDto userDetail = userService.fetchUserDetail(lect.getUserId());
        return userMapper.toUserResponseDto(lect, userDetail);
    }

    public List<UserResponseDto> getLecturerDetailsbyDepartmentId(UUID departmentId) {

        List<Lecturer> lecturers = lectRepo.findByDepartmentId(departmentId);
        List<UserResponseDto> output = new ArrayList<>();
        for (Lecturer lecturer : lecturers) {
            UserResponseDto dto = getLecturerDetails2(lecturer.getId());
            output.add(dto);
        }
        return output;
    }



    public List<UserResponseDto> getAllLecturers2(){
        List<Lecturer> lecturers = lectRepo.listAll();
        return lecturers.stream()
                .map(l -> getLecturerDetails2(l.getId()))
                .toList();
    }
}