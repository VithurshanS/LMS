package org.lms.Service;

import java.util.UUID;

import org.lms.Dto.AdminDetailDto;
import org.lms.Dto.UserDetailDto;
import org.lms.Dto.UserResponseDto;
import org.lms.Exceptions.ConflictException;
import org.lms.Exceptions.NotFoundException;
import org.lms.Mapper.UserMapper;
import org.lms.Model.Admin;
import org.lms.Repository.AdminRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AdminService {
    @Inject
    AdminRepository adminRepo;

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    public void createAdmin(UUID userId){
        if(adminRepo.findByUserId(userId) != null){
            throw new ConflictException("User already exists as admin");
        }
        Admin admin = new Admin(userId);
        adminRepo.persist(admin);
    }

    public AdminDetailDto getAdminDetail(UUID adminId){
        Admin admin = adminRepo.findById(adminId);
        if(admin == null){
            throw new NotFoundException("No admin found with ID: " + adminId);
        }
        
        UserDetailDto userDetail = userService.fetchUserDetail(admin.getUserId());
        return userMapper.toAdminDetailDto(admin, userDetail);
    }

    public UserResponseDto getAdminDetails(UUID adminId){
        Admin admin = adminRepo.findByUserId(adminId);
        if(admin == null){
            throw new NotFoundException("No admin found with user ID: " + adminId);
        }
        
        UserDetailDto userDetail = userService.fetchUserDetail(admin.getUserId());
        return userMapper.toUserResponseDto(admin, userDetail);
    }

}
