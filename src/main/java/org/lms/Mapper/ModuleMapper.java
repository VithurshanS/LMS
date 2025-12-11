package org.lms.Mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lms.Dto.ModuleDetailDto;
import org.lms.Model.Module;
import org.lms.Repository.EnrollmentRepository;

@ApplicationScoped
public class ModuleMapper {

    @Inject
    EnrollmentRepository enrollmentRepository;

    public ModuleDetailDto toDto(Module module) {
        if (module == null) {
            return null;
        }

        ModuleDetailDto dto = new ModuleDetailDto();
        dto.moduleId = module.getId();
        dto.moduleCode = module.getModule_code();
        dto.name = module.getName();
        dto.enrollmentLimit = module.getLimit();
        
        dto.departmentId = (module.getDepartment() != null) 
                ? module.getDepartment().getId() 
                : null;

        dto.lecturerId = (module.getLecturer() != null) 
                ? module.getLecturer().getId() 
                : null;

        dto.adminId = (module.getCreatedby() != null) 
                ? module.getCreatedby().getId() 
                : null;

        dto.enrolledCount = enrollmentRepository.countByModuleId(module.getId());

        return dto;
    }

    public ModuleDetailDto toDtoWithoutEnrollmentCount(Module module) {
        if (module == null) {
            return null;
        }

        ModuleDetailDto dto = new ModuleDetailDto();
        dto.moduleId = module.getId();
        dto.moduleCode = module.getModule_code();
        dto.name = module.getName();
        dto.enrollmentLimit = module.getLimit();
        
        dto.departmentId = (module.getDepartment() != null) 
                ? module.getDepartment().getId() 
                : null;

        dto.lecturerId = (module.getLecturer() != null) 
                ? module.getLecturer().getId() 
                : null;

        dto.adminId = (module.getCreatedby() != null) 
                ? module.getCreatedby().getId() 
                : null;

        dto.enrolledCount = 0;

        return dto;
    }
}
