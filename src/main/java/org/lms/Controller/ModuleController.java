package org.lms.Controller;
import java.util.List;
import java.util.UUID;

import org.lms.Dto.LectAssignRequest;
import org.lms.Dto.ModuleCreateRequest;
import org.lms.Dto.ModuleDetailDto;
import org.lms.Service.ModuleService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/modules")
public class ModuleController {

    @Inject
    ModuleService moduleService;
    @RolesAllowed({"admin","lecturer","student"})
    @GET
    public Response getModules(@QueryParam("lecturerId") UUID lectId, @QueryParam("studentId") UUID studentId, @QueryParam("departmentId") UUID deptId){
            List<ModuleDetailDto> modules;
            if (lectId != null) {
                modules = moduleService.getModulesByLectId(lectId);
            } else if (studentId != null) {
                modules = moduleService.getModulesByStudentId(studentId);
            } else if (deptId != null) {
                modules = moduleService.getModulesByDeptId(deptId);
            } else {
                return Response.status(400).entity("Please provide lecturerId, studentId, or departmentId query parameter").build();
            }
            return Response.ok(modules).build();

    }
    @RolesAllowed("admin")
    @POST 
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createModule(@Valid ModuleCreateRequest request){

            return Response.status(201).entity(moduleService.createModule(request.code, request.name, request.limit, request.departmentId, request.adminId)).build();


    }




    @RolesAllowed("admin")
    @PATCH
    @Path("/assign-lecturer") 
    public Response assignlecturer(@Valid LectAssignRequest req){ //admin
            moduleService.assignLecturer(req.moduleId,req.lecturerId);
            return Response.ok("lecturer assigned").build();


    }
}
