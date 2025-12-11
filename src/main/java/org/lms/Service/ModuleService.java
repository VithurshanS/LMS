package org.lms.Service;

import java.util.List;
import java.util.UUID;

import org.lms.Dto.ModuleDetailDto;
import org.lms.Exceptions.NotFoundException;
import org.lms.Mapper.ModuleMapper;
import org.lms.Model.Admin;
import org.lms.Model.Department;
import org.lms.Model.Lecturer;
import org.lms.Model.Module;
import org.lms.Repository.AdminRepository;
import org.lms.Repository.DepartmentRepository;
import org.lms.Repository.EnrollmentRepository;
import org.lms.Repository.LecturerRepository;
import org.lms.Repository.ModuleRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ModuleService {

    @Inject
    ModuleRepository moduleRepo;

    @Inject
    DepartmentRepository deptRepo;

    @Inject
    AdminRepository adminRepo;

    @Inject
    LecturerRepository lectRepo;

    @Inject
    StudentService studentService;

    @Inject
    EnrollmentRepository enrollmentRepository;

    @Inject
    AdminService adminService;

    @Inject
    LecturerService lecturerService;

    @Inject
    ModuleMapper moduleMapper;

    //Get

    @Transactional
    public ModuleDetailDto getModule(UUID moduleId) {
        Module mod = moduleRepo.findById(moduleId);
        if (mod == null) {
            throw new NotFoundException("Module not found for ID: " + moduleId);
        }
        return moduleMapper.toDto(mod);
    }


    public List<ModuleDetailDto> getModulesByDeptId(UUID deptId){
        List<Module> modules = moduleRepo.findByDepartmentId(deptId);
        return modules.stream()
                .map(moduleMapper::toDto)
                .toList();
    }

    public List<ModuleDetailDto> getModulesByLectId(UUID lectId){
        List<Module> modules = moduleRepo.findByLecturerId(lectId);
        return modules.stream()
                .map(moduleMapper::toDto)
                .toList();
    }

    public List<ModuleDetailDto> getModulesByStudentId(UUID studentId){
        List<Module> modules = enrollmentRepository.findModulesByStudentId(studentId);
        return modules.stream()
                .map(moduleMapper::toDto)
                .toList();
    }


    //create

    @Transactional
    public ModuleDetailDto createModule(String code, String name, int limit, UUID departmentId, UUID adminId) {
        Department dept = deptRepo.findById(departmentId);
        if (dept == null) {
            throw new NotFoundException("Department not found");
        }

        Admin admin = adminRepo.findById(adminId);
        if (admin == null) {
            throw new NotFoundException("Admin not found. Only Admins can create modules.");
        }

        Module module = new Module();
        module.setModule_code(code);
        module.setName(name);
        module.setLimit(limit);
        module.setDepartment(dept);
        module.setCreatedby(admin);

        moduleRepo.persist(module);
        return moduleMapper.toDto(module);
    }

    //patch


    @Transactional
    public void assignLecturer(UUID moduleId, UUID lecturerId) {
        Module module = moduleRepo.findById(moduleId);
        if (module == null) {
            throw new NotFoundException("Module not found");
        }

        Lecturer lecturer = lectRepo.findById(lecturerId);
        if (lecturer == null) {
            throw new NotFoundException("Lecturer not found");
        }

        module.setLecturer(lecturer);
//        ModuleDetailDto mod = moduleService.getModule(mod)
    }




//    public List<StudentDetailDto> getEnrolledStudents(UUID moduleId) {
//        Module module = moduleRepo.findById(moduleId);
//        if (module == null) {
//            throw new NotFoundException("Module not found");
//        }
//
//        List<Enrollment> enrollments = module.getModuleEnrollments();
//
//        if (enrollments == null) {
//            return Collections.emptyList();
//        }
//
//
//        return enrollments.stream()
//                .map(Enrollment::getStudent)
//                .map(student -> studentService.getStudentDetails(student.getId()))
//                .collect(Collectors.toList());
//    }

    public List<Module> getAllModules() {
        return moduleRepo.listAll();
    }

    public boolean doesModulehasThisLecturer(UUID moduleId,UUID lecturerId){
        Lecturer lect = moduleRepo.findById(moduleId).getLecturer();
        if(lect!=null){
            return lect.getId().equals(lecturerId);
        }
        return false;
    }

    public boolean doesthismodulehaveStudent(UUID moduleId,UUID studentId){
        return moduleRepo.hasEnrollmentForStudentAndModule(studentId,moduleId);
    }
}