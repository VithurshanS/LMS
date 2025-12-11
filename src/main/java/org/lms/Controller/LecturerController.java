package org.lms.Controller;


import java.util.List;
import java.util.UUID;

import org.lms.Dto.UserResponseDto;
import org.lms.Service.LecturerService;
import org.lms.Service.ModuleService;
import org.lms.Service.UserService;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;




@Path("/api/lecturers")
public class LecturerController {
    @Inject
    ModuleService moduleService;

    @Inject
    LecturerService lecturerService;

    @Inject
    UserService userService;

    @Inject
    SecurityIdentity identity;

    @RolesAllowed("admin")
    @PATCH
    @Path("/{id}/approve")
    public Response approve(@PathParam("id") UUID lecturerId){
        String message = userService.approveUser(lecturerId);
        return Response.ok(message).build();
    }

    @RolesAllowed("admin")
    @GET //admin
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllLecturers() {
            List<UserResponseDto> lecturers = lecturerService.getAllLecturers2();
            return Response.ok(lecturers).build();
    }


    @RolesAllowed("admin")
    @GET//admin
    public Response getAllLecturersbyDeptId(@QueryParam("departmentId") UUID deptid) {
            List<UserResponseDto> lecturers = lecturerService.getLecturerDetailsbyDepartmentId(deptid);
            return Response.ok(lecturers).build();
    }


    @RolesAllowed({"admin","lecturer","student"})
    @GET
    @Path("/{id}") //lecturer +admin
    public Response getLecturerById(@PathParam("id") UUID lecturerId) {
            UserResponseDto dto = lecturerService.getLecturerDetails2(lecturerId);
            return Response.ok(dto).build();
    }


    @RolesAllowed("lecturer")
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String accessLectureResource(){
        return "I am a lecturer Resource";
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public

}
