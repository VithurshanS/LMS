package org.lms.Mapper;

import jakarta.enterprise.context.ApplicationScoped;
import org.lms.Dto.DepartmentDetailDto;
import org.lms.Model.Department;


@ApplicationScoped
public class DepartmentMapper {

    public DepartmentDetailDto toDto(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentDetailDto dto = new DepartmentDetailDto();
        dto.departmentId = department.getId();
        dto.name = department.getName();
        return dto;
    }

    public Department toEntity(DepartmentDetailDto dto) {
        if (dto == null) {
            return null;
        }

        Department department = new Department(dto.name);
        return department;
    }
}
