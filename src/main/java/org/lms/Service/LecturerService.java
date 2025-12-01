package org.lms.Service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lms.Model.Department;
import org.lms.Model.Lecturer;
import org.lms.Model.Module;
import org.lms.Repository.LecturerRepository;

import java.util.List;
import java.util.UUID;
class UserDetail{
    public boolean isActive;
    public String userName;
    public String firstName;
    public String lastName;
    public String role;
}

class LecturerDetail{
    public UUID lecturerId;
    public boolean isActive;
    public String userName;
    public String firstName;
    public String lastName;
    public Department department;
}

@ApplicationScoped
public class LecturerService {
    @Inject
    LecturerRepository lectRepo;

    public void createLecturer(UUID userId, Department dept){
        lectRepo.persist(new Lecturer(userId,dept));
    }

    public List<Module> getAssignedModules(UUID lecturerId){
        return lectRepo.findById(lecturerId).getTeachingModules();
    }

//    public LecturerDetail getLecturerDetails(UUID lecturerId){
//
//    }
}
