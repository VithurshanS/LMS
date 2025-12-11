package org.lms.Controller;


import org.lms.Dto.EnrollRequest;
import org.lms.Dto.UnenrollRequest;
import org.lms.Model.Enrollment;
import org.lms.Service.EnrollmentService;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/api/enrollments")
public class EnrollmentController {

    @Inject
    EnrollmentService enrollmentService;

    @RolesAllowed("student")
    @POST
    public Response enrollStudent(@Valid EnrollRequest req) {
            Enrollment enr = enrollmentService.enrollStudent(req.studentId, req.moduleId);
            return Response.status(201).entity(enr).build();

    }

    @RolesAllowed("admin")
    @DELETE
    public Response unenrollStudent(@Valid UnenrollRequest ur){
            enrollmentService.unenrollStudent(ur.studentId,ur.moduleId);
            return Response.ok().build();
    }
}
