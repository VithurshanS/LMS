package org.lms.Controller;

import java.util.UUID;

import org.lms.Dto.DeptCreateRequest;
import org.lms.Service.DepartmentService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/departments")
public class DepartmentController {

    @Inject
    DepartmentService departmentService;


    @RolesAllowed("admin")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDept(@Valid DeptCreateRequest req) {
        return Response.status(201).entity(departmentService.createDepartment(req.name)).build();
    }

    @PermitAll
    @GET
    public Response getAllDept(){

        return Response.ok(departmentService.getAllDepartments()).build();

    }


    @RolesAllowed({"admin","student","lecturer"})
    @GET
    @Path("/{id}")
    public Response getDepartmentbyId(@PathParam("id") UUID departmentId){

        return Response.ok(departmentService.getDepartmentById(departmentId)).build();

    }
}
