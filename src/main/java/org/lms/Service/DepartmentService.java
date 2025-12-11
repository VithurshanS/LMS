package org.lms.Service;

import java.util.List;
import java.util.UUID;

import org.lms.Dto.DepartmentDetailDto;
import org.lms.Exceptions.ConflictException;
import org.lms.Exceptions.NotFoundException;
import org.lms.Mapper.DepartmentMapper;
import org.lms.Model.Department;
import org.lms.Repository.DepartmentRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DepartmentService {

    @Inject
    DepartmentRepository deptRepo;

    @Inject
    DepartmentMapper departmentMapper;


    @Transactional
    public DepartmentDetailDto createDepartment(String name) {
        if(deptRepo.existByName(name)){
            throw new ConflictException("department name already exists"+" "+name);
        }

        Department dept = new Department(name);
        deptRepo.persist(dept);
        return departmentMapper.toDto(dept);
    }

    public List<DepartmentDetailDto> getAllDepartments() {
        List<Department> deps = deptRepo.listAll();
        return deps.stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDetailDto getDepartmentById(UUID id) {
        Department dept = deptRepo.findById(id);
        if (dept == null) {
            throw new NotFoundException("department not exist on this id"+" "+id.toString());
        }
        return departmentMapper.toDto(dept);
    }
}