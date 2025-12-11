package org.lms.Controller;


import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path("/api/admins")
public class AdminController {


    @RolesAllowed("admin")
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String testAdminResourse(){
        return "I am a admin resource";
    }




}
