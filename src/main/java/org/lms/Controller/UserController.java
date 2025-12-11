package org.lms.Controller;


import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.lms.Dto.*;
import org.keycloak.admin.client.Keycloak;
import org.lms.Service.DepartmentService;
import org.lms.Service.UserService;

@Path("/auth")
public class UserController {



    @Inject
    UserService userService;


    @GET
    @Path("/sample")
    @Produces(MediaType.APPLICATION_JSON)
    public Response see(){
        return Response.ok("test").build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@Valid RegistrationRequestDto userDto) {
        if (userDto != null && userDto.role != null) {
            userDto.role = userDto.role.toLowerCase();
        }
        String message = userService.registerUser(userDto,"ironone");
        return Response.status(201).entity(message).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequestDto credentials){
        LoginResponceDto loginResponse = userService.loginUser(credentials);
        return Response.ok(loginResponse).build();
    }


    @GET
    @Path("/me")
    public Response getLoginedUser(){
        UserResponseDto profile = userService.getProfileFromToken();
        return Response.ok(profile).build();
    }


    @PATCH
    @Path("/control")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response controllUser(@Valid ControlRequest cr){
        userService.controllUserAccess(cr.id, cr.control, cr.role);
        return Response.ok("User access modified successfully").build();
    }


}