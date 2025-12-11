package org.lms.Controller;


import java.util.List;
import java.util.UUID;

import org.lms.Dto.UserResponseDto;
import org.lms.Service.EnrollmentService;
import org.lms.Service.ModuleService;
import org.lms.Service.StudentService;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/students")
public class StudentController {

    @Inject
    SecurityIdentity identity;

    @Inject
    ModuleService moduleService;

    @Inject
    StudentService studentService;

    @RolesAllowed("student")
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String accessStudentResource(){
        return "I am a student Resource";
    }

    @Inject
    EnrollmentService enrollmentService;





    @RolesAllowed({"lecturer","admin"})
    @GET
    public Response getStudents(@QueryParam("moduleId") UUID moduleId, @QueryParam("departmentId") UUID departmentId) {

            List<UserResponseDto> students;
            if (moduleId != null) {
                students = studentService.getStudentsByModuleId(moduleId);
            } else if (departmentId != null) {
                students = studentService.getStudentDetailsbyDepartmentId(departmentId);
            } else {
                students = studentService.getAllStudents2();
            }
            return Response.ok(students).build();

    }







    @RolesAllowed({"admin","student","lecturer"})
    @GET
    @Path("/{id}") //lecturer + admin +student
    public Response getStudentById(@PathParam("id") UUID studentId) {
            UserResponseDto dto = studentService.getStudentDetails2(studentId);
            if (dto == null) {
                return Response.status(404).entity("Student not found").build();
            }
            return Response.ok(dto).build();
    }




}
