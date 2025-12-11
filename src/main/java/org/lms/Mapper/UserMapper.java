package org.lms.Mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.lms.Dto.AdminDetailDto;
import org.lms.Dto.UserDetailDto;
import org.lms.Dto.UserResponseDto;
import org.lms.Model.Admin;
import org.lms.Model.Lecturer;
import org.lms.Model.Student;

@ApplicationScoped
public class UserMapper {


    public UserResponseDto toUserResponseDto(Student student, UserDetailDto userDetail) {
        if (student == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();
        dto.id = student.getId();
        
        if (student.getDepartment() != null) {
            dto.departmentId = student.getDepartment().getId();
        }

        if (userDetail != null) {
            dto.username = userDetail.userName;
            dto.email = userDetail.email;
            dto.firstName = userDetail.firstName;
            dto.lastName = userDetail.lastName;
            dto.isActive = userDetail.isActive;
            
            if (userDetail.clientRole != null && !userDetail.clientRole.isEmpty()) {
                dto.role = userDetail.clientRole.get(0).toUpperCase();
            }
        }

        return dto;
    }

    public UserResponseDto toUserResponseDto(Lecturer lecturer, UserDetailDto userDetail) {
        if (lecturer == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();
        dto.id = lecturer.getId();
        
        if (lecturer.getDepartment() != null) {
            dto.departmentId = lecturer.getDepartment().getId();
        }

        if (userDetail != null) {
            dto.username = userDetail.userName;
            dto.email = userDetail.email;
            dto.firstName = userDetail.firstName;
            dto.lastName = userDetail.lastName;
            dto.isActive = userDetail.isActive;
            
            if (userDetail.clientRole != null && !userDetail.clientRole.isEmpty()) {
                dto.role = userDetail.clientRole.get(0).toUpperCase();
            }
        }

        return dto;
    }


    public UserResponseDto toUserResponseDto(Admin admin, UserDetailDto userDetail) {
        if (admin == null) {
            return null;
        }

        UserResponseDto dto = new UserResponseDto();
        dto.id = admin.getId();

        if (userDetail != null) {
            dto.username = userDetail.userName;
            dto.email = userDetail.email;
            dto.firstName = userDetail.firstName;
            dto.lastName = userDetail.lastName;
            dto.isActive = userDetail.isActive;
            
            if (userDetail.clientRole != null && !userDetail.clientRole.isEmpty()) {
                dto.role = userDetail.clientRole.get(0).toUpperCase();
            }
        }

        return dto;
    }

    public AdminDetailDto toAdminDetailDto(Admin admin, UserDetailDto userDetail) {
        if (admin == null) {
            return null;
        }

        AdminDetailDto dto = new AdminDetailDto();
        dto.adminId = admin.getId();
        
        if (userDetail != null) {
            dto.adminUsername = userDetail.userName;
        }

        return dto;
    }
}
